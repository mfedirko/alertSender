package dao;

import model.entity.api.AlertDetail;

import java.util.Optional;

public interface AlertDAOCustom {
    Optional<AlertDetail> findByAlertID(int alertId);
}
