package model.entity.cta.customeralerts;


import model.entity.api.AlertPreference;

import javax.persistence.*;

@Entity
@Table(name = "cta_alrt_pref")
@Access(AccessType.FIELD)
public class CtaAlertPreference extends AlertPreference {


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;





    @Column(name ="incl_accssbl")
    private boolean include_accessibility_alerts;


    public boolean isInclude_accessibility_alerts() {
        return include_accessibility_alerts;
    }

    public void setInclude_accessibility_alerts(boolean include_accessibility_alerts) {
        this.include_accessibility_alerts = include_accessibility_alerts;
    }


    public boolean isExclude_inactive_alerts() {
        return exclude_inactive_alerts;
    }

    public void setExclude_inactive_alerts(boolean exclude_inactive_alerts) {
        this.exclude_inactive_alerts = exclude_inactive_alerts;
    }


    @Column(name = "active_only")
    private boolean exclude_inactive_alerts;


    public boolean isInclude_planned_alerts() {
        return include_planned_alerts;
    }

    public void setInclude_planned_alerts(boolean include_planned_alerts) {
        this.include_planned_alerts = include_planned_alerts;
    }


    @Column(name = "incl_planned")
    private boolean include_planned_alerts;



    public int getNotifiedStopIDs() {
        return notifiedStopIDs;
    }

    public void setNotifiedStopIDs(int notifiedStopIDs) {
        this.notifiedStopIDs = notifiedStopIDs;
    }

    @Column(name="stop")
    private int notifiedStopIDs;


//    @ElementCollection(targetClass = CtaTrainRoute.class)
//    @CollectionTable(name = "train_routes", joinColumns = @JoinColumn(name = "route_id"))
//    @Column(name = "route", nullable = false)
//    @Enumerated(EnumType.STRING)
//    public Set<CtaTrainRoute> getNotifiedRoutes() {
//        return notifiedRoutes;
//    }
//
//    public void setNotifiedRoutes(Set<CtaTrainRoute> notifiedRoutes) {
//        this.notifiedRoutes = notifiedRoutes;
//    }
//
//
//
//    private Set<CtaTrainRoute> notifiedRoutes;

}
