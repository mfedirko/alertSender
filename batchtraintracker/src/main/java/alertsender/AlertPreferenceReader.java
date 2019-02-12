package alertsender;

import dao.AlertPreferenceDAO;
import model.entity.api.AlertPreference;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;

//import model.entity.api.AlertPreference;
//import model.entity.cta.customeralerts.CtaAlertPreference;

public class AlertPreferenceReader implements ItemReader<AlertPreference> {
    public AlertPreferenceReader(AlertPreferenceDAO ctaAlertPreferenceDAO){
        this.ctaAlertPreferenceDAO = ctaAlertPreferenceDAO;
    }

    private AlertPreferenceDAO ctaAlertPreferenceDAO;

    private Iterable<AlertPreference> allPreferences;
    private Iterator<AlertPreference> preferenceIterator;

    @Override
    public AlertPreference read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (allPreferences == null) {
            allPreferences =ctaAlertPreferenceDAO.findAll();
            preferenceIterator = allPreferences.iterator();
        }
        if ( ! preferenceIterator.hasNext()) return  null;
        return preferenceIterator.next();
    }
}
