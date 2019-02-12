package model.cta.customeralerts;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import model.Direction;
import model.entity.api.Stop;
import model.entity.cta.customeralerts.CtaTrainRoute;
import org.apache.commons.lang3.tuple.Pair;

@JsonDeserialize(using = CtaStopResponseDeserializer.class)
public class CtaStopResponse implements Stop {

    @Override
    public String toString() {
        return "CtaStopResponse{" +
                "stopId=" + stopId +
                ", mapId=" + mapId +
                ", coordinates=" + coordinates +
                ", direction=" + direction +
                ", stopName='" + stopName + '\'' +
                ", ctaTrainRoute=" + ctaTrainRoute +
                '}';
    }

    public CtaStopResponse() {
    }

    public Integer getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }



    public Pair<Double, Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Pair<Double, Double> coordinates) {
        this.coordinates = coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public CtaTrainRoute getCtaTrainRoute() {
        return ctaTrainRoute;
    }

    public void setCtaTrainRoute(CtaTrainRoute ctaTrainRoute) {
        this.ctaTrainRoute = ctaTrainRoute;
    }

    public CtaStopResponse(int stopId, int mapId, Pair<Double, Double> coordinates, Direction direction, String stopName, CtaTrainRoute ctaTrainRoute) {
        this.stopId = stopId;
        this.mapId = mapId;
        this.coordinates = coordinates;
        this.direction = direction;
        this.stopName = stopName;
        this.ctaTrainRoute = ctaTrainRoute;
    }

    private int stopId;
    private int mapId;
    private Pair<Double, Double> coordinates;
    private Direction direction;
    private String stopName;
    private CtaTrainRoute ctaTrainRoute;





}
