package integration.cta.traintracker.client.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import model.entity.TrainTrackerArrival;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TrainTrackerArrivalResponseDeserializer extends JsonDeserializer<TrainTrackerArrivalResponse> {
    @Override
    public TrainTrackerArrivalResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        node = node.get("ctatt");
        System.out.println(node);
        TrainTrackerArrivalResponse.TrainTrackerError errorCode = TrainTrackerArrivalResponse.TrainTrackerError.fromNumber(node.get("errCd").asInt());

        String errorMsg = node.get("errNm").textValue();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd' 'HH:mm:ss");
        Date timeStamp = null;
        try {
            timeStamp = sdf1.parse(node.get("tmst").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<TrainTrackerArrival> arrivals = new ArrayList<>();

        JsonNode alertsNode = node.get("eta");
        if (alertsNode != null && alertsNode.isContainerNode()) {
            Iterator<JsonNode> alertsIt = alertsNode.elements();

            while (alertsIt.hasNext()) {
                JsonNode alertNode = alertsIt.next();
                int stpId = alertNode.get("stpId").asInt();
                String station = alertNode.get("staNm").asText();
                String dest = alertNode.get("destNm").asText();
                int runNum = alertNode.get("rn").asInt();
                boolean isApp = alertNode.get("isApp").asInt() > 0;
                boolean isSchd = alertNode.get("isSch").asInt() > 0;
                boolean isDelay = alertNode.get("isDly").asInt() > 0;
                boolean hasFault = alertNode.get("isFlt").asInt() > 0;

                String arrivalTS = alertNode.get("arrT").asText();
                Date arrivalTSDate;
                try {
                    arrivalTSDate = sdf1.parse(arrivalTS);
                } catch (ParseException e) {
                    System.err.println(e);
                    continue; // No point in sending response without arrival time
                }
                TrainTrackerArrival arrival = new TrainTrackerArrival();
                arrival.setStopId(stpId);
                arrival.setApproaching(isApp);
                arrival.setArrivalTime(arrivalTSDate);
                arrival.setDelayed(isDelay);
                arrival.setDestination(dest);
                arrival.setHasFault(hasFault);
                arrival.setRunNumber(runNum);
                arrival.setNonLivePrediction(isSchd);
                arrival.setStationName(station);

                arrivals.add(arrival);
            }
        }

        TrainTrackerArrivalResponse resp = new TrainTrackerArrivalResponse();
        resp.setErrorCode(errorCode);
        resp.setErrorMsg(errorMsg);
        resp.setTimestamp(timeStamp);
        resp.setTrainTrackerArrivalList(arrivals);
        return resp;
    }
}
