package alertsender;

import model.cta.customeralerts.CtaStopResponse;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class CtaStopRESTReader implements ItemReader<CtaStopResponse> {

    private final String apiUrl;
    private final RestTemplate restTemplate;

    private int nextStopIndex;
    private List<CtaStopResponse> stopsData;

    public CtaStopRESTReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextStopIndex = 0;
    }

    @Override
    public CtaStopResponse read() throws Exception {
        if (stopsDataIsNotInitialized()) {
            stopsData = fetchStopsFromAPI();
        }

        CtaStopResponse nextStudent = null;

        if (nextStopIndex < stopsData.size()) {
            nextStudent = stopsData.get(nextStopIndex);
            nextStopIndex++;
        }

        return nextStudent;
    }

    private boolean stopsDataIsNotInitialized() {
        return this.stopsData == null;
    }


    private List<CtaStopResponse> fetchStopsFromAPI(){
        ResponseEntity<CtaStopResponse[]> response = restTemplate.getForEntity(
                apiUrl,
                CtaStopResponse[].class
        );
       CtaStopResponse[] stops = response.getBody();
        return Arrays.asList(stops);
    }



}
