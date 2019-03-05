package batch;


//import dao.AlertNotificationDAO;
//import model.entity.api.AlertNotification;
//import dao.AlertNotificationDAO;
//import model.entity.api.AlertNotification;
//import dao.AlertNotificationDAO;
//import dao.AlertNotificationDAO;
//import dao.AlertNotificationDAO;
//import model.entity.api.AlertNotification;
//import dao.AlertNotificationDAO;
//import model.entity.api.AlertNotification;
import dao.AlertNotificationDAO;
import model.entity.api.AlertNotification;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

public class AlertNotificationReader implements ItemReader<AlertNotification> {

    private AlertNotificationDAO dao;

    public AlertNotificationReader(AlertNotificationDAO dao){
        this.dao = dao;
    }

    private List<AlertNotification> allNewAlerts;

    int curIndex = 0;

    @Override
    public AlertNotification read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (allNewAlerts == null) allNewAlerts = dao.findAlertsByStatus(AlertNotification.Status.NEW);
        if (curIndex < allNewAlerts.size()) return allNewAlerts.get(curIndex++);
        else  return  null;
    }
}
