package com.example.service.alerts;



import model.entity.CtaStop;
import model.TrainTrackerAlert;

import java.util.List;

public interface TrainTrackerAlertsService extends AlertsService {
    List<TrainTrackerAlert> findActiveAlerts(Integer routeId);

    List<TrainTrackerAlert> findActiveAlerts(Integer routeId, CtaStop stop);
}
