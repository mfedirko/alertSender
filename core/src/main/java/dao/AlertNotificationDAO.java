package dao;

import model.entity.api.AlertNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertNotificationDAO extends JpaRepository<AlertNotification,Integer>, AlertNotificationDAOCustom {


}
