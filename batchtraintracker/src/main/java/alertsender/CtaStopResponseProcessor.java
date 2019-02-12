package alertsender;

//import model.entity.cta.customeralerts.CtaStop;
//import model.cta.customeralerts.CtaStopResponse;
import model.cta.customeralerts.CtaStopResponse;
import model.entity.cta.customeralerts.CtaStop;
import org.springframework.batch.item.ItemProcessor;

public class CtaStopResponseProcessor implements ItemProcessor<CtaStopResponse, CtaStop> {
    @Override
    public CtaStop process(CtaStopResponse ctaStopResponse) throws Exception {
        // Null return indicates do not process this record
        if (ctaStopResponse == null) return null;
        if (ctaStopResponse.getStopId() == null) return null;

        String routeId = ctaStopResponse.getCtaTrainRoute() != null ? ctaStopResponse.getCtaTrainRoute().getRouteId() : null;
        CtaStop stop = new CtaStop();
        stop.setRouteId(routeId);
        stop.setDirection(ctaStopResponse.getDirection());
        stop.setStopName(ctaStopResponse.getStopName());
        stop.setStopId(ctaStopResponse.getMapId());
        stop.setRealStopId(ctaStopResponse.getStopId());

        //Validate required fields
        if (stop.getRouteId() == null || stop.getStopId() == null || stop.getRealStopId() == null) return null;

        System.out.println(stop);
        return stop;
    }
}
