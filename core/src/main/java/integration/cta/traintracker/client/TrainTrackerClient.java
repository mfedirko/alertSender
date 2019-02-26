package integration.cta.traintracker.client;



import integration.cta.traintracker.client.response.TrainTrackerArrivalResponse;
import model.entity.TrainTrackerArrival;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TrainTrackerClient {
    //Constants
    private static final String HTTP500_API_PROVIDER_FAIL="API provider failed to process request";
    private static final String HTTP4xx_CLIENT_ERROR="Request was invalid or unauthorized";


    private RestTemplate restTemplate;
    private final String ALERT_URL;
    private final String API_KEY;

    public TrainTrackerClient(RestTemplate restTemplate, @NotNull  String ALERT_URL,
                              @NotNull String API_KEY) {
        this.restTemplate = restTemplate;
        this.ALERT_URL = ALERT_URL;
        this.API_KEY = API_KEY;
    }

    public List<TrainTrackerArrival> arrivalsByStop(int stopId, int maxResCount) throws Exception{
        String url = new StringBuilder(ALERT_URL)
                .append(String.format("?key=%s",API_KEY))
                .append("&outputType=JSON")
                .append(String.format("&max=%d",maxResCount))
                .append(String.format("&stpid=%s",stopId))
                .toString();

        ResponseEntity<TrainTrackerArrivalResponse> resp = restTemplate.getForEntity(url, TrainTrackerArrivalResponse.class);
        evaluateRespBody(resp);
        return resp.getBody().getTrainTrackerArrivalList();
    }

    public List<TrainTrackerArrival> arrivalsByStation(int mapId, int maxResCount) throws Exception {

        String url = new StringBuilder(ALERT_URL)
                .append(String.format("?key=%s",API_KEY))
                .append("&outputType=JSON")
                .append(String.format("&max=%d",maxResCount))
                .append(String.format("&mapid=%s",mapId))
                .toString();

        ResponseEntity<TrainTrackerArrivalResponse> resp = restTemplate.getForEntity(url, TrainTrackerArrivalResponse.class);
        evaluateRespBody(resp);
        return resp.getBody().getTrainTrackerArrivalList();
    }

    private void evaluateRespBody(ResponseEntity resp) throws Exception{
        if (resp.getBody() == null) {
            if (resp.getStatusCode().is5xxServerError()) throw new Exception(HTTP500_API_PROVIDER_FAIL);
            if (resp.getStatusCode().is4xxClientError()) throw new Exception(HTTP4xx_CLIENT_ERROR);
        }
    }
}
