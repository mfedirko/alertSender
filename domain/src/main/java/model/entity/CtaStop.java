package model.entity;


import model.Direction;
import model.Stop;

import javax.persistence.*;

@Entity
@Table(name = "STOP_REF")
public class CtaStop implements Stop {

    @Column(name = "route_id")
    private String routeId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "direction")
    private Direction direction;

    @Column(name = "stop_name")
    private String stopName;

    @Id
    @Column(name = "id")
    private Integer stopId;

    public CtaStop(String routeId, Direction direction, String stopName, Integer stopId) {
        this.routeId = routeId;
        this.direction = direction;
        this.stopName = stopName;
        this.stopId = stopId;
    }

    public CtaStop() {
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
