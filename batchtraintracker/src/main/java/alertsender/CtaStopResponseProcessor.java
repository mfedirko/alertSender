package alertsender;

import model.entity.CtaStop;
import model.CtaStopResponse;
import org.springframework.batch.item.ItemProcessor;

public class CtaStopResponseProcessor implements ItemProcessor<CtaStopResponse, CtaStop> {
    @Override
    public CtaStop process(CtaStopResponse ctaStopResponse) throws Exception {
        // Null return indicates do not process this record
        if (ctaStopResponse == null) return null;
        if (ctaStopResponse.getStopId() == null) return null;

        String routeId = ctaStopResponse.getCtaTrainRoute() != null ? ctaStopResponse.getCtaTrainRoute().getRouteId() : null;
        return new CtaStop(routeId,ctaStopResponse.getDirection(),ctaStopResponse.getStopName(), ctaStopResponse.getStopId());
    }
}
