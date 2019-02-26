package model.entity.cta.customeralerts;

import model.entity.api.Alert;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CTAAlert implements Alert {

    private int id;
    private String headline;
    private String shortDesc;

    public CTAAlert() {
    }

    @Override
    public String toString() {
        return "CTAAlert{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", fullDesc='" + fullDesc + '\'' +
                ", severity=" + severity +
                ", eventStart=" + eventStart +
                ", eventEnd=" + eventEnd +
                ", TBD=" + TBD +
                ", isMajorAlert=" + isMajorAlert +
                ", alertURL='" + alertURL + '\'' +
                ", impactedServiceIds=" + impactedServiceIds +
                '}';
    }

    public CTAAlert(int id, String headline, String shortDesc, String fullDesc, AlertSeverity severity, Date eventStart, Date eventEnd, boolean TBD, boolean isMajorAlert, String alertURL, String impactedServiceIds) {
        this.id = id;
        this.headline = headline;
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.severity = severity;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.TBD = TBD;
        this.isMajorAlert = isMajorAlert;
        this.alertURL = alertURL;
        this.impactedServiceIds = impactedServiceIds;
    }

    private String fullDesc;

    public int getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public AlertSeverity getSeverity() {
        return severity;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public boolean isTBD() {
        return TBD;
    }

    public boolean isMajorAlert() {
        return isMajorAlert;
    }

    public String getAlertURL() {
        return alertURL;
    }

    public String getImpactedServiceIds() {
        return impactedServiceIds;
    }

    private AlertSeverity severity;

    private Date eventStart;
    private Date eventEnd;
    private boolean TBD;
    private boolean isMajorAlert;
    private String alertURL;

    private String impactedServiceIds;

    @Override
    public String getDescription() {
        return shortDesc;
    }


    @Override
    public Object[] impactedRouteIds() {
        return new String[]{impactedServiceIds};
    }

    @Override
    public Date startTime() {
        return eventStart;
    }

    @Override
    public Date endTime() {
        return eventEnd;
    }

    public enum AlertSeverity {

        INFO_ACCESSIBILITY(1,19),
        PLANNED(20,39),
        MINOR_UNPLANNED(40,59),
        SIGNIF_UNPLANNED(60,79),
        MAJOR_UNPLANNED(80,99);

        static Map<Pair<Integer,Integer>,AlertSeverity> rangeMap = new HashMap<>();
        static {
            for (AlertSeverity alertSeverity : AlertSeverity.values()){
                rangeMap.put(Pair.of(alertSeverity.lowerBound,alertSeverity.upperBound) , alertSeverity );
            }
        }
        private final int lowerBound;
        private final int upperBound;
        AlertSeverity(int lowerBound,int upperBound){
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public static AlertSeverity fromNumber(int number){
            Optional<Map.Entry<Pair<Integer,Integer>,AlertSeverity>> sev =
                    rangeMap.entrySet().stream()
                            .filter(entry->
                                    entry.getKey().getLeft() <= number
                                            && entry.getKey().getRight() >= number)
                            .findFirst();

            if (sev.isPresent()) return sev.get().getValue();
            else return null;
        }
    }
}
