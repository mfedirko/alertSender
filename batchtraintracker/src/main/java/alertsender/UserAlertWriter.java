package alertsender;

import dao.AlertDAO;
import dao.AlertNotificationDAO;
import model.entity.AlertEntity;
import model.entity.AlertNotification;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserAlertWriter implements ItemWriter<AlertNotification> {

    @Autowired
    private AlertDAO alertDAO;
    @Autowired
    private AlertNotificationDAO alertNotificationDAO;

    @Override
    public void write(List<? extends AlertNotification> list) throws Exception {
        for (AlertNotification notification : list){
            for (AlertEntity alertEntity : notification.getAlerts()){
                try {
                    alertDAO.save(alertEntity);
                }
                catch (Exception e){
                    System.err.println(e);
                    notification.getAlerts().remove(alertEntity);
                }
            }
            alertNotificationDAO.save(notification);
        }

    }
}
