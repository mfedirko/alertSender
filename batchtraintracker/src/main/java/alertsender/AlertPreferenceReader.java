package alertsender;

import dao.CtaAlertPreferenceDAO;
import model.entity.CtaAlertPreference;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class AlertPreferenceReader implements ItemReader<CtaAlertPreference> {
    public AlertPreferenceReader(CtaAlertPreferenceDAO ctaAlertPreferenceDAO){
        this.ctaAlertPreferenceDAO = ctaAlertPreferenceDAO;
    }

    private CtaAlertPreferenceDAO ctaAlertPreferenceDAO;

    private Iterable<CtaAlertPreference> allPreferences;
    private Iterator<CtaAlertPreference> preferenceIterator;

    @Override
    public CtaAlertPreference read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (allPreferences == null) {
            allPreferences =ctaAlertPreferenceDAO.findAll();
            preferenceIterator = allPreferences.iterator();
        }
        if ( ! preferenceIterator.hasNext()) return  null;
        return preferenceIterator.next();
    }
}
