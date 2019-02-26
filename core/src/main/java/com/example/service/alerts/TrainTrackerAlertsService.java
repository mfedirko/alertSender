package com.example.service.alerts;



import model.entity.cta.customeralerts.CTAAlert;
import model.entity.cta.customeralerts.CtaStop;

import java.util.List;

public interface TrainTrackerAlertsService extends AlertsService {
    List<CTAAlert> findActiveAlerts(Integer routeId);

    List<CTAAlert> findActiveAlerts(Integer routeId, CtaStop stop);
}
