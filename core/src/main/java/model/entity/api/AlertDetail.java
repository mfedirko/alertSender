package model.entity.api;

import model.constant.AlertSource;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alert")
@Inheritance(strategy = InheritanceType.JOINED)
public class AlertDetail {


    @Column(name = "alert_id")
    private int id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int dbId;

    @Column(name = "description")
    private String description;

    @Column(name = "alert_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Override
    public String toString() {
        return "AlertDetail{" +
                "id=" + id +
                ", dbId=" + dbId +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isMajor=" + isMajor +
                ", url='" + url + '\'' +
                '}';
    }



    @Column(name = "alert_src")
    @Enumerated
    private AlertSource alertSource;





    @Column(name = "alert_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "is_major")
    private boolean isMajor;

    @Column(name = "alert_url")
    private String url;


    @ManyToOne(optional = false)
    @JoinColumn(name = "alert_ntfy_id", referencedColumnName = "id")
//    @org.hibernate.annotations.ForeignKey( name = "alert_ibfk_1")
    private AlertNotification alertNotification;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public AlertSource getAlertSource() {
        return alertSource;
    }

    public void setAlertSource(AlertSource alertSource) {
        this.alertSource = alertSource;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public AlertNotification getAlertNotification() {
        return alertNotification;
    }

    public void setAlertNotification(AlertNotification alertNotification) {
        this.alertNotification = alertNotification;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isMajor() {
        return isMajor;
    }

    public void setMajor(boolean major) {
        isMajor = major;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
