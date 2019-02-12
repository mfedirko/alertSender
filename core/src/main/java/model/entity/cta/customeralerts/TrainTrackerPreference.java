package model.entity.cta.customeralerts;

import model.entity.api.AlertPreference;

import javax.persistence.*;

@Entity
@Table(name = "train_tracker_pref")
@Access(AccessType.FIELD)
public class TrainTrackerPreference extends AlertPreference {
    public TrainTrackerPreference(long id, int stopId, int stationId) {
        this.id = id;
        this.stopId = stopId;
        this.stationId = stationId;
    }

    public TrainTrackerPreference() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "stop_id")
    private Integer stopId;

    @Column(name = "station_id")
    private Integer stationId;

    @Column(name = "max_res_count")
    private Integer maxResCount;

    public Integer getMaxResCount() {
        return maxResCount;
    }

    public void setMaxResCount(Integer maxResCount) {
        this.maxResCount = maxResCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }
}
