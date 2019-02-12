package model.entity.api;

import java.util.Date;

public interface Alert {
    int getId();
    String getDescription();
    Object[] impactedRouteIds();
    Date startTime();
    Date endTime();

}
