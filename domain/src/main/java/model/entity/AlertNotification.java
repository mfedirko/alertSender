package model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "USR_ALERT")
public class AlertNotification {


    @OneToMany
    public Set<AlertEntity> getAlerts() {
        return alerts;
    }


    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }



    @OneToOne
    public CtaAlertPreference getPreference() {
        return preference;
    }


    @CreationTimestamp
    @Column(name = "CRE_TS")
    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }




    @UpdateTimestamp
    @Column(name = "UPD_TS")
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }


    @Enumerated(value = EnumType.STRING)
    public Status getProcessingStatus() {
        return processingStatus;
    }


    public void setId(long id) {
        this.id = id;
    }





    private long id;

    private Set<AlertEntity> alerts;


    private CtaAlertPreference preference;


    private Timestamp creationTimestamp;


    private Timestamp updateTimestamp;


    private Status processingStatus;


    public void setPreference(CtaAlertPreference preference) {
        this.preference = preference;
    }
    public void setProcessingStatus(Status processingStatus) {
        this.processingStatus = processingStatus;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public void setAlerts(Set<AlertEntity> alerts) {
        this.alerts = alerts;
    }



    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public enum  Status {
        NEW,
        PROCESSING,
        SENT,
        FAILED;
    }

}
