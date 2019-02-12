package model.entity.api;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "usr_alert")
@Access(AccessType.PROPERTY)
public class AlertNotification {


    @OneToMany(mappedBy = "alertNotification", cascade = CascadeType.PERSIST,
    fetch = FetchType.EAGER)
//    @JoinColumn(referencedColumnName = "id",name = "alert_ntfy_id")
    public List<AlertDetail> getAlerts() {
        return alerts;
    }


    @Override
    public String toString() {
        return "AlertNotification{" +
                "id=" + id +
                ", alerts=" + alerts +
                ", preference=" + preference +
                ", creationTimestamp=" + creationTimestamp +
                ", updateTimestamp=" + updateTimestamp +
                ", processingStatus=" + processingStatus +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }




    @OneToOne
    @JoinColumn(name = "alert_pref_id", referencedColumnName = "id")
    public AlertPreference getPreference() {
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
    @Column(name = "procs_status")
    public Status getProcessingStatus() {
        return processingStatus;
    }


    public void setId(long id) {
        this.id = id;
    }






    private long id;

    private List<AlertDetail> alerts;


    private AlertPreference preference;


    private Timestamp creationTimestamp;


    private Timestamp updateTimestamp;


    private Status processingStatus;


    public void setPreference(AlertPreference preference) {
        this.preference = preference;
    }
    public void setProcessingStatus(Status processingStatus) {
        this.processingStatus = processingStatus;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public void setAlerts(List<AlertDetail> alerts) {
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
