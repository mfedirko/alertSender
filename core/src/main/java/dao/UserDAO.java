package dao;

import model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<AppUser,Integer>, UserDAOCustom {
}
