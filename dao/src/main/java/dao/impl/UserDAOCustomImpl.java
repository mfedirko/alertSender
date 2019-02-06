package dao.impl;

import dao.UserDAOCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDAOCustomImpl implements UserDAOCustom {
    @PersistenceContext
    private EntityManager entityManager;
}
