package batch;

//import dao.AlertDAO;
//import integration.cta.traintracker.client.TrainTrackerClient;
//import model.constant.AlertSource;
//import model.entity.cta.customeralerts.CTAAlert;

import dao.AlertDAO;
import integration.cta.traintracker.client.CustomerAlertsClient;
import integration.cta.traintracker.client.TrainTrackerClient;
import model.constant.AlertSource;
import model.entity.cta.customeralerts.CTAAlert;
import model.entity.TrainTrackerArrival;
import model.entity.api.Alert;
import model.entity.api.AlertDetail;
import model.entity.api.AlertNotification;
import model.entity.api.AlertPreference;
import model.entity.cta.customeralerts.CtaAlertPreference;
import model.entity.cta.customeralerts.TrainTrackerPreference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//import model.entity.TrainTrackerArrival;
//import model.entity.api.Alert;
//import model.entity.api.AlertDetail;
//import model.entity.api.AlertNotification;
//import model.entity.api.AlertPreference;
//import model.entity.cta.customeralerts.CtaAlertPreference;

public class UserAlertProcessor implements ItemProcessor<AlertPreference, AlertNotification> {

    private Logger logger = LoggerFactory.getLogger(UserAlertProcessor.class);


    private CustomerAlertsClient customerAlertsClient;
    private AlertDAO alertDAO;
    private TrainTrackerClient trainTrackerClient;

    public UserAlertProcessor(CustomerAlertsClient customerAlertsClient,
                              TrainTrackerClient trainTrackerClient,
                              AlertDAO alertDAO){
        this.customerAlertsClient = customerAlertsClient;
        this.trainTrackerClient = trainTrackerClient;
        this.alertDAO = alertDAO;
    }

    public void setAlertNotificationDAO(AlertDAO alertDAO) {
        this.alertDAO = alertDAO;
    }

    public void setCustomerAlertsClient(CustomerAlertsClient customerAlertsClient) {
        this.customerAlertsClient = customerAlertsClient;
    }


    /*
    * Processes alert preference into alert notification
     */
    @Override
    @NotFound(action = NotFoundAction.IGNORE)
    public AlertNotification process(AlertPreference alertPreference) throws Exception {
        logger.debug("Processing: {}",alertPreference);
        if (alertPreference instanceof CtaAlertPreference){
            CtaAlertPreference casted = (CtaAlertPreference) alertPreference;
            return process(casted);
        }
        if (alertPreference instanceof TrainTrackerPreference ){
            TrainTrackerPreference casted = (TrainTrackerPreference) alertPreference;
            return process(casted);
        }
        else return null;

    }

//    private AlertNotification processForTrainTrackerAlertPreference(TrainTrackerAlertPreference ttAlertPref) throws Exception {
//
//    }

    //TODO process for Train tracker arrivals preference

    private AlertNotification process(TrainTrackerPreference ctaAlertPreference) throws Exception{
        List<TrainTrackerArrival> allAlerts =fetchAlertsFromAPI(ctaAlertPreference);
        logger.debug("Fetched from API {}",allAlerts.size());
        logger.debug(" Fetched from API {}",allAlerts);
        TrainTrackerArrival[] alerts = allAlerts.toArray(new TrainTrackerArrival[0]);
        if (ctaAlertPreference.isPreventDuplicateNotifications()) {
            alerts= removePreviouslyNotifiedAlerts(alerts);
        }
        if (alerts.length > 0) {
            AlertNotification notification = new AlertNotification();
            notification.setCreationTimestamp(new Timestamp(new Date().getTime()));
            notification.setPreference(ctaAlertPreference);
            notification.setProcessingStatus(AlertNotification.Status.NEW);
            List<AlertDetail> alertEntities = mapToAlertEntity(alerts, notification);
            notification.setAlerts(alertEntities);
            logger.debug("Processor fetched CTA Alerts: {}",notification.toString());
            return notification;
        }
        else return null;
    }

    private AlertNotification process(CtaAlertPreference ctaAlertPreference) throws Exception{
        CTAAlert[] allAlerts =fetchAlertsFromAPI(ctaAlertPreference);
        logger.debug("{}",allAlerts.length);
        logger.debug("{}",Arrays.asList(allAlerts));


        CTAAlert[] alerts = allAlerts;
        if (ctaAlertPreference.isPreventDuplicateNotifications()) {
            alerts= removePreviouslyNotifiedAlerts(alerts);
        }
        if (alerts.length > 0) {
            AlertNotification notification = new AlertNotification();
            notification.setCreationTimestamp(new Timestamp(new Date().getTime()));
            notification.setPreference(ctaAlertPreference);
            notification.setProcessingStatus(AlertNotification.Status.NEW);
            List<AlertDetail> alertEntities = mapToAlertEntity(alerts, notification);
            notification.setAlerts(alertEntities);
            logger.debug("Processor fetched CTA Alerts: {}",notification.toString());
            return notification;
        }
        else return null;
    }

    @NotFound(action = NotFoundAction.IGNORE)
    private List<AlertDetail> mapToAlertEntity(TrainTrackerArrival[] CTAAlerts, AlertNotification notification){
        List<AlertDetail> entities = new ArrayList<>();
        for (TrainTrackerArrival alert : CTAAlerts){
            AlertDetail e = new AlertDetail();
            e.setDescription(alert.getDescription());
            e.setAlertSource(AlertSource.CTA_TRAIN_TRACKER);
            e.setAlertNotification(notification);
            entities.add(e);
        }
        return entities;
    }



    @NotFound(action = NotFoundAction.IGNORE)
    private List<AlertDetail> mapToAlertEntity(CTAAlert[] CTAAlerts, AlertNotification notification){
        List<AlertDetail> entities = new ArrayList<>();
        for (CTAAlert alert : CTAAlerts){
            AlertDetail e = new AlertDetail();
            e.setDescription(alert.getDescription());
            e.setEndTime(alert.getEventEnd());
            e.setStartTime(alert.getEventStart());
            e.setId(alert.getId());
            e.setAlertSource(AlertSource.CTA_CUSTOMER_ALERTS);
            e.setMajor(alert.isMajorAlert());
            e.setUrl(alert.getAlertURL());
            e.setAlertNotification(notification);
            entities.add(e);
        }
        return entities;
    }

    private CTAAlert[] fetchAlertsFromAPI(CtaAlertPreference preference) throws Exception{
                return  customerAlertsClient.alerts(new int[]{preference.getNotifiedStopIDs()},preference.isExclude_inactive_alerts(),
                        preference.isInclude_accessibility_alerts(),preference.isInclude_planned_alerts());

    }

    private List<TrainTrackerArrival> fetchAlertsFromAPI(TrainTrackerPreference preference) throws Exception{
        if (preference.getStopId() != null && preference.getStopId() != 0) return trainTrackerClient.arrivalsByStop(preference.getStopId(), preference.getMaxResCount());
        else if (preference.getStationId() != null && preference.getStationId() != 0) return trainTrackerClient.arrivalsByStation(preference.getStationId(),preference.getMaxResCount());
        else return new ArrayList<>();
    }

    protected  <T extends Alert> T[] removePreviouslyNotifiedAlerts(T[] alerts){
        T[] cloned = alerts.clone();

        int index = 0;
        int bIndex = 0;
        for (int i = 0; i < alerts.length; i++){
            logger.debug("{}",alerts[i]);
            if ( !alertDAO.findByAlertID((alerts[i]).getId()).isPresent()){
               cloned[bIndex++]=alerts[i];
            }
        }

        return Arrays.copyOfRange(cloned,0,bIndex);
    }


}
