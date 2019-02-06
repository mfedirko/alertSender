package dao.impl;

import dao.AlertDAO;
import dao.AlertDAOCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AlertDAOImpl implements AlertDAOCustom {
    @PersistenceContext
    private EntityManager entityManager;
}
