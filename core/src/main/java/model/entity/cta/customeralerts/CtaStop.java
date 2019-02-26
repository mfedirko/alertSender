package model.entity.cta.customeralerts;


import model.constant.Direction;
import model.entity.api.Stop;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "stop_ref")
@Access(AccessType.FIELD)
public class CtaStop implements Stop {

    @Column(name = "route_id")
    private String routeId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "direction")
    private Direction direction;

    @Column(name = "stop_name")
    private String stopName;


    @Column(name = "station_id")
    private Integer stopId;

    @Id
    @Column(name = "stop_id")
    private Integer realStopId;


    @CreationTimestamp
    @Column(name = "cre_ts",nullable = false)
    private Timestamp creationTime;

    @UpdateTimestamp
    @Column(name = "upd_ts")
    private Timestamp updateTime;

    @Override
    public String toString() {
        return "CtaStop{" +
                "routeId='" + routeId + '\'' +
                ", direction=" + direction +
                ", stopName='" + stopName + '\'' +
                ", stopId=" + stopId +
                '}';
    }

    public CtaStop(String routeId, Direction direction, String stopName, Integer stopId) {
        this.routeId = routeId;
        this.direction = direction;
        this.stopName = stopName;
        this.stopId = stopId;
    }

    public CtaStop() {
    }

    public Integer getRealStopId() {
        return realStopId;
    }

    public void setRealStopId(Integer realStopId) {
        this.realStopId = realStopId;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String getStopName() {
        return stopName;
    }

    @Override
    public Integer getStopId() {
        return stopId;
    }


}
