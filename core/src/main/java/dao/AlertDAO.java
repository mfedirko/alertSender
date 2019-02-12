package dao;

import model.entity.api.AlertDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertDAO extends JpaRepository<AlertDetail,Integer>, AlertDAOCustom {
}
