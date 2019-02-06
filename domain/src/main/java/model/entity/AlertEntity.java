package model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ALERT")
public class AlertEntity {
    @Id
    @Column(name = "alert_id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "alert_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "alert_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "is_major")
    private boolean isMajor;

    @Column(name = "alert_url")
    private String url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
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
