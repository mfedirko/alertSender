package com.example.service.alerts;



import model.Alert;
import model.Stop;

import java.io.Serializable;
import java.util.List;

public interface AlertsService  <T extends Alert,  S extends Stop>  {
    /*
    Check if there are any alerts for today for route, stop, severity,
    */
    List<T> findActiveAlerts(Serializable routeId);
   List<T> findActiveAlerts(Serializable routeId, S stop);
}
