package dao.impl;

import dao.AlertPreferenceDAOCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AlertPreferenceDAOCustomImpl implements AlertPreferenceDAOCustom {
    @PersistenceContext(unitName = "persistnc")
    private EntityManager entityManager;
}
