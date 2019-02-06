package dao;

import model.entity.CtaAlertPreference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface CtaAlertPreferenceDAO extends CrudRepository<CtaAlertPreference,Integer>, CtaAlertPreferenceDAOCustom {

}
