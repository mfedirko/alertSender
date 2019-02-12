package dao;

//import model.entity.cta.customeralerts.CtaAlertPreference;
//import model.entity.api.AlertPreference;
//import model.entity.api.AlertPreference;
//import model.entity.api.AlertPreference;
//import model.entity.api.AlertPreference;
//import model.entity.api.AlertPreference;
import model.entity.api.AlertPreference;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlertPreferenceDAO extends JpaRepository<AlertPreference,Integer>, AlertPreferenceDAOCustom {

}
