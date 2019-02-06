package dao;

import model.entity.AlertEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AlertDAO extends CrudRepository<AlertEntity,Integer>, AlertDAOCustom {
}
