package dao.impl;

import dao.AlertNotificationDAOCustom;
import model.entity.AlertNotification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AlertNotificationDAOCustomImpl implements AlertNotificationDAOCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AlertNotification> findByAlertId(int alertId) {
        return entityManager
                .createQuery("SELECT a from AlertNotification WHERE a.alertId = :alertId")
                .setParameter("alertId",alertId)
                .getResultList();
    }
}
