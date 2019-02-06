package integration.cta.traintracker.client;


import model.TrainTrackerAlert;
import model.TrainTrackerAlertResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainTrackerAlertsClient {

    @Autowired
   private RestTemplate restTemplate;

    //Constants
    private final String ALERT_URL;
    private static final String HTTP500_API_PROVIDER_FAIL="API provider failed to process request";
    private static final String HTTP4xx_CLIENT_ERROR="Request was invalid or unauthorized";


    private SimpleDateFormat ALERTS_API_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");


    public TrainTrackerAlertsClient(
             String ALERT_URL,
            final RestTemplate restTemplate){

        this.ALERT_URL = ALERT_URL;
        this.restTemplate = restTemplate;
    }



    public TrainTrackerAlert[] alerts(String[] routeIds ) throws Exception{

        return alerts(routeIds, true, false ,false );
    }

    public TrainTrackerAlert[]  alerts(String[] routeIds, boolean activeonly, Boolean accessibility,
                                            Boolean includePlanned
                                            ) throws Exception{

        Date tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        String url =
                new StringBuilder(ALERT_URL)
                .append("?outputType=JSON")
                .append("&routeid=" + String.join(",", routeIds))
                .append("&activeonly=" + activeonly)
                .append("&accessibility=" + accessibility)
                .append("&planned=" + includePlanned)
                .append("&byStartDate=" + ALERTS_API_DATE_FORMAT.format(tomorrow))
                .toString();
        ResponseEntity<TrainTrackerAlertResponse> resp = restTemplate.getForEntity(url,TrainTrackerAlertResponse.class);
        if (resp.getBody() == null) {
            if (resp.getStatusCode().is5xxServerError()) throw new Exception(HTTP500_API_PROVIDER_FAIL);
            if (resp.getStatusCode().is4xxClientError()) throw new Exception(HTTP4xx_CLIENT_ERROR);
        }
        return resp.getBody().getTrainTrackerAlerts();
    }


    public TrainTrackerAlert[]  alerts(int[] stationIds ) throws Exception{
        Date tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        return alerts(stationIds, true, false ,false );
    }

    public TrainTrackerAlert[]  alerts(int[] stationIds, Boolean activeonly, Boolean accessibility,
                                            Boolean includePlanned
    ) throws Exception{

        String commaSeparatedIds = Arrays.toString(stationIds).replaceAll("[ \\[\\]]*","");


        Date tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        String url =
                new StringBuilder(ALERT_URL)
                        .append("?outputType=JSON")
                        .append("&stationid=" + commaSeparatedIds)
                        .append("&activeonly=" + activeonly)
                        .append("&accessibility=" + accessibility)
                        .append("&planned=" + includePlanned)
                        .append("&byStartDate=" + ALERTS_API_DATE_FORMAT.format(tomorrow))
                        .toString();
        ResponseEntity<TrainTrackerAlertResponse> resp = restTemplate.getForEntity(url,TrainTrackerAlertResponse.class);
        if (resp.getBody() == null) {
            if (resp.getStatusCode().is5xxServerError()) throw new Exception(HTTP500_API_PROVIDER_FAIL);
            if (resp.getStatusCode().is4xxClientError()) throw new Exception(HTTP4xx_CLIENT_ERROR);
        }
        else{
          verifyErrorCodeNotFail(resp);
        }
        return resp.getBody().getTrainTrackerAlerts();

    }

    private void verifyErrorCodeNotFail(ResponseEntity<TrainTrackerAlertResponse> resp) throws Exception{
        List<TrainTrackerAlertResponse.TrainTrackerAlertError> failureCodes =
                Arrays.asList(TrainTrackerAlertResponse.TrainTrackerAlertError.values())
                        .stream().filter(a->a != TrainTrackerAlertResponse.TrainTrackerAlertError.NO_ACTIVE_ALERTS && a != TrainTrackerAlertResponse.TrainTrackerAlertError.NO_ERROR)
                        .collect(Collectors.toList());
        if (failureCodes.contains(resp.getBody().getErrorCode())){
            throw new Exception(String.format("Failed to get alerts. {} - {}",resp.getBody().getErrorCode(), resp.getBody().getErrorMsg()));
        }
    }



}
