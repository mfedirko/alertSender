package batch;

import dao.AlertDAO;
import dao.AlertNotificationDAO;
import model.entity.api.AlertNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserAlertWriter implements ItemWriter<AlertNotification> {

    private Logger logger = LoggerFactory.getLogger(UserAlertWriter.class);
//    @Autowired
//    private SessionFactory sessionFactory;

    @Autowired
    private AlertDAO alertDAO;
    @Autowired
    private AlertNotificationDAO alertNotificationDAO;

    @Override
    public void write(List<? extends AlertNotification> list) throws Exception {
        for (AlertNotification notification : list){
            logger.debug("Saving notificaiton list: {}",list);
            AlertNotification saved = alertNotificationDAO.saveAndFlush(notification);
        }
//            Transaction txn = session.beginTransaction();
//            try {
//                session.save(notification);
//                session.flush();
//                session.clear();
//            }
//            catch (Exception e){
//                System.err.println(e.getMessage());
//                continue;
//            }
//            for (AlertDetail alertEntity : notification.getAlerts()){
//                    try {
//                        session.save(alertEntity);
//                        session.flush();
//                        session.clear();
//                    }
//                    catch (Exception e){
//                        System.err.println(e.getMessage());
//                        notification.getAlerts().remove(alertEntity);
//                    }
//
//            }
//
//
//
//            txn.commit();
//
//        }
//        session.close();

    }
}
