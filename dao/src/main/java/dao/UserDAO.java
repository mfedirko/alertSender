package dao;

import model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserDAO extends CrudRepository<User,Integer>, UserDAOCustom {
}
