package model.entity;

import model.entity.api.Alert;

import java.util.Date;

public class TrainTrackerArrival implements Alert {

    private Integer stopId;
    private String stationName;
    private String destination;
    private Integer runNumber;
    private String routeId;
    private Date arrivalTime;
    private Boolean isApproaching;
    private Boolean isDelayed;
    private Boolean hasFault;
    private Boolean isNonLivePrediction;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    @Override
    public String toString() {
        return "TrainTrackerArrival{" +
                "stopId=" + stopId +
                ", stationName='" + stationName + '\'' +
                ", destination='" + destination + '\'' +
                ", runNumber=" + runNumber +
                ", routeId='" + routeId + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", isApproaching=" + isApproaching +
                ", isDelayed=" + isDelayed +
                ", hasFault=" + hasFault +
                ", isNonLivePrediction=" + isNonLivePrediction +
                ", id=" + id +
                '}';
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(Integer runNumber) {
        this.runNumber = runNumber;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Boolean getApproaching() {
        return isApproaching;
    }

    public void setApproaching(Boolean approaching) {
        isApproaching = approaching;
    }

    public Boolean getDelayed() {
        return isDelayed;
    }

    public void setDelayed(Boolean delayed) {
        isDelayed = delayed;
    }

    public Boolean getHasFault() {
        return hasFault;
    }

    public void setHasFault(Boolean hasFault) {
        this.hasFault = hasFault;
    }

    public Boolean getNonLivePrediction() {
        return isNonLivePrediction;
    }

    public void setNonLivePrediction(Boolean nonLivePrediction) {
        isNonLivePrediction = nonLivePrediction;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDescription() {

        StringBuilder description = new StringBuilder();
        description.append(String.format("Train number %d toward %s ",this.getRunNumber(), this.getDestination()));
        if (this.getApproaching()) description.append("is approaching ");
        else                        description.append(String.format(" expected to arrive at %s at ",this.getArrivalTime()));
        description.append(String.format("%s.",this.getStationName()));
        if (this.getNonLivePrediction()) description.append(" (non-live prediction)");
        if (this.getHasFault()) description.append(" Train has a fault! ");
        if (this.getDelayed()) description.append(" Train will be delayed.");
        return description.toString();
    }

    @Override
    public Object[] impactedRouteIds() {
        return new Object[]{getRouteId()};
    }

    @Override
    public Date startTime() {
        return null;
    }

    @Override
    public Date endTime() {
        return null;
    }
}
