package model;

import java.util.Date;

public interface Alert {
    String getDescription();
    Object[] impactedRouteIds();
    Date startTime();
    Date endTime();

}
