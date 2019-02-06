package model.entity;

import java.util.HashMap;
import java.util.Map;

public enum CtaTrainRoute {
    RED ("red"),
    GREEN("g"),
    BROWN("brn"),
    BLUE("blue"),
    PINK("pnk"),
    ORANGE("o"),
    YELLOW("y"),
    PURPLE("p"),
    PURPLE_EXPRESS("pexp");

    public static Map<String, CtaTrainRoute> getRouteIdMap() {
        return routeIdMap;
    }

    public String getRouteId() {
        return routeId;
    }

    private static Map<String, CtaTrainRoute> routeIdMap = new HashMap<String, CtaTrainRoute>();
    static {
        for (CtaTrainRoute route : CtaTrainRoute.values()){
            routeIdMap.put(route.routeId, route);
        }
    }

    private String routeId;

    CtaTrainRoute(String routeId){
        this.routeId = routeId;
    }

    public static CtaTrainRoute fromRouteId(String routeId){
        return routeIdMap.get(routeId);
    }


    public String toString() {
        return routeId;
    }


}
