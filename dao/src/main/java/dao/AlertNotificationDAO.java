package dao;

import model.entity.AlertNotification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AlertNotificationDAO extends CrudRepository<AlertNotification,Integer>, AlertNotificationDAOCustom {


}
