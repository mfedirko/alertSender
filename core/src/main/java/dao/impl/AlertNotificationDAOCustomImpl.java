package dao.impl;

import dao.AlertNotificationDAOCustom;
import model.entity.api.AlertNotification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//import model.entity.api.AlertNotification;
//import model.entity.api.AlertNotification;

@Repository
public class AlertNotificationDAOCustomImpl implements AlertNotificationDAOCustom {

    @PersistenceContext(unitName = "persistnc")
    private EntityManager entityManager;



    @Override
    public List<AlertNotification> findByAlertId(int alertId) {
        return entityManager
                .createQuery("SELECT a from AlertNotification a WHERE a.alertId = :alertId")
                .setParameter("alertId",alertId)
                .getResultList();
    }

    @Override
    public List<AlertNotification> findAlertsByStatus(AlertNotification.Status status) {
        return entityManager
                .createQuery("SELECT a from AlertNotification a WHERE a.processingStatus= :status")
                .setParameter("status",status)
                .getResultList();
    }


}
