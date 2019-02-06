package dao;

import model.entity.AlertNotification;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlertNotificationDAOCustom {
    //    @Query("SELECT a from AlertNotification WHERE a.alertId = :alertId")
    List<AlertNotification> findByAlertId(int alertId);
}
