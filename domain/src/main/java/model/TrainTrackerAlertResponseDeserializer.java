package model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TrainTrackerAlertResponseDeserializer extends JsonDeserializer<TrainTrackerAlertResponse> {
    @Override
    public TrainTrackerAlertResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        node = node.get("CTAAlerts");

        TrainTrackerAlertResponse.TrainTrackerAlertError errorCode = TrainTrackerAlertResponse.TrainTrackerAlertError.fromNumber(node.get("ErrorCode").asInt());

        String errorMsg = node.get("ErrorMessage").textValue();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date timeStamp = null;
        try {
             timeStamp = sdf.parse(node.get("TimeStamp").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<TrainTrackerAlert> trainTrackerAlerts = new ArrayList<>();
        int curAlert = 0;
        JsonNode alertsNode = node.get("TrainTrackerAlert");
        if (alertsNode != null && alertsNode.isContainerNode()) {
            Iterator<JsonNode> alertsIt = alertsNode.elements();

            while (alertsIt.hasNext()) {
                JsonNode alertNode = alertsIt.next();

                int id = alertNode.get("AlertId").asInt();
                String headline = alertNode.get("Headline").asText();
                String shortDesc = alertNode.get("ShortDescription").asText();
                String fullDesc = alertNode.get("FullDescription").asText();
                TrainTrackerAlert.AlertSeverity severity = TrainTrackerAlert.AlertSeverity.fromNumber(alertNode.get("SeverityScore").asInt() );
                String impact = alertNode.get("Impact").asText();
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

                String evtStartStr = alertNode.get("EventStart").asText();
                String evtEndStr = alertNode.get("EventEnd").asText();
                Date eventStart = null;
                Date eventEnd = null;
                try {
                    if (evtStartStr != null) eventStart = sdf.parse(evtStartStr);
                    if (evtEndStr != null) eventEnd = sdf.parse(evtEndStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int isMajorAlertInt = alertNode.get("MajorAlert").asInt();
                boolean isMajorAlert = isMajorAlertInt > 0;

                int TBDint = alertNode.get("TBD").asInt();
                boolean isTBD = TBDint > 0;
                String alertURL = alertNode.get("AlertURL").asText();

                String impactedService = alertNode.get("ImpactedService").get("Service").get("ServiceId").asText();


                TrainTrackerAlert trainTrackerAlert = new TrainTrackerAlert(id, headline, shortDesc, fullDesc, severity, eventStart, eventEnd, isTBD, isMajorAlert, alertURL, impactedService);
                trainTrackerAlerts.add(trainTrackerAlert);

            }
        }
            return  new TrainTrackerAlertResponse(errorCode,errorMsg,timeStamp, trainTrackerAlerts.toArray(new TrainTrackerAlert[0]));
    }
}
