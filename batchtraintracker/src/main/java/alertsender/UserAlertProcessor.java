package alertsender;

import dao.AlertNotificationDAO;
import model.entity.AlertEntity;
import model.entity.AlertNotification;
import model.entity.CtaAlertPreference;
import integration.cta.traintracker.client.TrainTrackerAlertsClient;
import model.TrainTrackerAlert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.*;

public class UserAlertProcessor implements ItemProcessor<CtaAlertPreference, AlertNotification> {

    private Logger logger = LoggerFactory.getLogger(UserAlertProcessor.class);


    private TrainTrackerAlertsClient client;
    private AlertNotificationDAO alertNotificationDAO;

    public UserAlertProcessor(TrainTrackerAlertsClient client, AlertNotificationDAO alertNotificationDAO){
        this.client = client;
        this.alertNotificationDAO = alertNotificationDAO;
    }

    public void setAlertNotificationDAO(AlertNotificationDAO alertNotificationDAO) {
        this.alertNotificationDAO = alertNotificationDAO;
    }

    public void setClient(TrainTrackerAlertsClient client) {
        this.client = client;
    }



    @Override
    public AlertNotification process(CtaAlertPreference ctaAlertPreference) throws Exception {
        TrainTrackerAlert[] allAlerts =fetchAlertsFromAPI(ctaAlertPreference);
        TrainTrackerAlert[] alerts = removePreviouslyNotifiedAlerts(allAlerts);

        AlertNotification notification = new AlertNotification();
        notification.setCreationTimestamp(new Timestamp(new Date().getTime()));
        notification.setPreference(ctaAlertPreference);
        notification.setProcessingStatus(AlertNotification.Status.NEW);
        notification.setAlerts(mapToAlertEntity(alerts));
        return notification;
    }

    private Set<AlertEntity> mapToAlertEntity(TrainTrackerAlert[] trainTrackerAlerts){
        Set<AlertEntity> entities = new HashSet<>();
        for (TrainTrackerAlert alert : trainTrackerAlerts){
            AlertEntity e = new AlertEntity();
            e.setDescription(alert.getDescription());
            e.setEndTime(alert.getEventEnd());
            e.setStartTime(alert.getEventStart());
            e.setId(alert.getId());
            e.setMajor(alert.isMajorAlert());
            e.setUrl(alert.getAlertURL());
        }
        return entities;
    }

    private TrainTrackerAlert[] fetchAlertsFromAPI(CtaAlertPreference ctaAlertPreference) throws Exception{
        return  client.alerts(new int[]{ctaAlertPreference.getNotifiedStopIDs()},ctaAlertPreference.isExclude_inactive_alerts(),
                ctaAlertPreference.isInclude_accessibility_alerts(),ctaAlertPreference.isInclude_planned_alerts());
    }

    private TrainTrackerAlert[] removePreviouslyNotifiedAlerts(TrainTrackerAlert[] alerts){
        TrainTrackerAlert[] toSend = new TrainTrackerAlert[alerts.length];
        int index = 0;
        for (TrainTrackerAlert alert : alerts){
            if (alertNotificationDAO.findByAlertId(alert.getId()).isEmpty()){
                toSend[index++]=alert;
            }
        }
        return toSend;
    }


}
