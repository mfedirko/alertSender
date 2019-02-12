package model.cta.customeralerts;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.entity.TrainTrackerArrival;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrainTrackerArrivalResponseDeserializerTest {

    private TrainTrackerArrivalResponseDeserializer deserializer;
    private ObjectMapper objectMapper;

    @Before
    public void setup(){
        deserializer = new TrainTrackerArrivalResponseDeserializer();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void deserialize() throws Exception{
        String testStr = "{\"ctatt\":{\"tmst\":\"2019-02-09T01:09:51\",\"errCd\":\"0\",\"errNm\":null,\"eta\":[{\"staId\":\"41220\",\"stpId\":\"30234\",\"staNm\":\"Fullerton\",\"stpDe\":\"Service toward 95th/Dan Ryan\",\"rn\":\"930\",\"rt\":\"Red\",\"destSt\":\"30089\",\"destNm\":\"95th/Dan Ryan\",\"trDr\":\"5\",\"prdt\":\"2019-02-09T01:09:27\",\"arrT\":\"2019-02-09T01:14:27\",\"isApp\":\"0\",\"isSch\":\"0\",\"isDly\":\"1\",\"isFlt\":\"0\",\"flags\":null,\"lat\":\"41.94743\",\"lon\":\"-87.65363\",\"heading\":\"179\"},{\"staId\":\"41220\",\"stpId\":\"30234\",\"staNm\":\"Fullerton\",\"stpDe\":\"Service toward 95th/Dan Ryan\",\"rn\":\"931\",\"rt\":\"Red\",\"destSt\":\"30089\",\"destNm\":\"95th/Dan Ryan\",\"trDr\":\"5\",\"prdt\":\"2019-02-09T01:08:45\",\"arrT\":\"2019-02-09T01:23:45\",\"isApp\":\"0\",\"isSch\":\"0\",\"isDly\":\"0\",\"isFlt\":\"0\",\"flags\":null,\"lat\":\"41.9835\",\"lon\":\"-87.65884\",\"heading\":\"178\"}]}}";
        InputStream stream = new ByteArrayInputStream(testStr.getBytes(StandardCharsets.UTF_8));

        JsonParser parser = objectMapper.getFactory().createParser(stream);
        DeserializationContext ctxt = objectMapper.getDeserializationContext();
        TrainTrackerArrivalResponse resp = deserializer.deserialize(parser,ctxt);
        System.out.println(resp);
        assertEquals(resp.getTrainTrackerArrivalList().size(),2);
        assertEquals(resp.getErrorCode(), TrainTrackerArrivalResponse.TrainTrackerError.NO_ERROR);

        List<TrainTrackerArrival> expected = new ArrayList<>();
        TrainTrackerArrival arrival1 = new TrainTrackerArrival();
        arrival1.setStationName("Fullerton");
        arrival1.setNonLivePrediction(false);
        arrival1.setDestination("95th/Dan Ryan");
        arrival1.setDelayed(true);
        arrival1.setNonLivePrediction(false);
        arrival1.setHasFault(false);
        arrival1.setApproaching(false);
        arrival1.setRunNumber(930);
        arrival1.setArrivalTime(new Date(119,1,9,1,14,27));
        arrival1.setStopId(30234);

        TrainTrackerArrival arrival2 = new TrainTrackerArrival();
        arrival2.setStationName("Fullerton");
        arrival2.setNonLivePrediction(false);
        arrival2.setDestination("95th/Dan Ryan");
        arrival2.setDelayed(false);
        arrival2.setNonLivePrediction(false);
        arrival2.setHasFault(false);
        arrival2.setApproaching(false);
        arrival2.setRunNumber(931);
        arrival2.setArrivalTime(new Date(119,1,9,1,23,45));
        arrival2.setStopId(30234);

        TrainTrackerArrival actual1= resp.getTrainTrackerArrivalList().get(0);
        TrainTrackerArrival actual2= resp.getTrainTrackerArrivalList().get(1);

        assertEquals(actual1.getArrivalTime(),arrival1.getArrivalTime());
        assertEquals(actual1.getApproaching(),arrival1.getApproaching());
        assertEquals(actual1.getDelayed(),arrival1.getDelayed());
        assertEquals(actual1.getDestination(),arrival1.getDestination());
        assertEquals(actual1.getStationName(),arrival1.getStationName());
        assertEquals(actual1.getStopId(),arrival1.getStopId());

        assertEquals(actual2.getArrivalTime(),arrival2.getArrivalTime());
        assertEquals(actual2.getApproaching(),arrival2.getApproaching());
        assertEquals(actual2.getDelayed(),arrival2.getDelayed());
        assertEquals(actual2.getDestination(),arrival2.getDestination());
        assertEquals(actual2.getStationName(),arrival2.getStationName());
        assertEquals(actual2.getStopId(),arrival2.getStopId());
//        2019-02-09T01:14:27
    }
}