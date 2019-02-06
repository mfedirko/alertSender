package dao.impl;

import dao.AlertDAO;
import dao.CtaAlertPreferenceDAOCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CtaAlertPreferenceDAOCustomImpl implements CtaAlertPreferenceDAOCustom {
    @PersistenceContext
    private EntityManager entityManager;
}
