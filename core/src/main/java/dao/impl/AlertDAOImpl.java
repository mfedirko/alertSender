package dao.impl;

import dao.AlertDAOCustom;
import model.entity.api.AlertDetail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AlertDAOImpl implements AlertDAOCustom {
    @PersistenceContext(unitName = "persistnc")
    private EntityManager entityManager;

    @Override
    public Optional<AlertDetail> findByAlertID(int alertId) {
        List<AlertDetail> alerts = entityManager
                .createQuery("SELECT a from AlertDetail a WHERE a.id = :alertId")
                .setParameter("alertId",alertId)
                .getResultList();
        if (alerts.isEmpty()) return Optional.empty();
        else return Optional.of(alerts.get(0));

    }
}
