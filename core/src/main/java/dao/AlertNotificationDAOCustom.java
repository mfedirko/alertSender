package dao;

//import model.entity.api.AlertNotification;

//import model.entity.api.AlertNotification;

//import model.entity.api.AlertNotification;

import model.entity.api.AlertNotification;

import java.util.List;

public interface AlertNotificationDAOCustom {
    //    @Query("SELECT a from AlertNotification WHERE a.alertId = :alertId")
    List<AlertNotification> findByAlertId(int alertId);

    List<AlertNotification> findAlertsByStatus(AlertNotification.Status status);
}
