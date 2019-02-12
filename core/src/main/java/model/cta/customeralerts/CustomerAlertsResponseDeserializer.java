package model.cta.customeralerts;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CustomerAlertsResponseDeserializer extends JsonDeserializer<CustomerAlertsResponse> {
    @Override
    public CustomerAlertsResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        System.out.println("Deserializing to CustomerAlertsResponse");
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        node = node.get("CTAAlerts");
        System.out.println(node);
        CustomerAlertsResponse.CustAlertsError errorCode = CustomerAlertsResponse.CustAlertsError.fromNumber(node.get("ErrorCode").asInt());

        String errorMsg = node.get("ErrorMessage").textValue();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date timeStamp = null;
        try {
             timeStamp = sdf.parse(node.get("TimeStamp").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<CTAAlert> CTAAlerts = new ArrayList<>();
        int curAlert = 0;
        JsonNode alertsNode = node.get("Alert");
        if (alertsNode != null && alertsNode.isContainerNode()) {
            Iterator<JsonNode> alertsIt = alertsNode.elements();

            while (alertsIt.hasNext()) {
                JsonNode alertNode = alertsIt.next();

                int id = alertNode.get("AlertId").asInt();
                String headline = alertNode.get("Headline").asText();
                String shortDesc = alertNode.get("ShortDescription").asText();
                String fullDesc = alertNode.get("FullDescription").asText();
                CTAAlert.AlertSeverity severity = CTAAlert.AlertSeverity.fromNumber(alertNode.get("SeverityScore").asInt() );
                String impact = alertNode.get("Impact").asText();
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

                String evtStartStr = alertNode.get("EventStart").asText();
                String evtEndStr = alertNode.get("EventEnd").asText();
                Date eventStart = null;
                Date eventEnd = null;
                try {
                    if (evtStartStr != null) eventStart = sdf2.parse(evtStartStr);
                    if (evtEndStr != null) eventEnd = sdf2.parse(evtEndStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int isMajorAlertInt = alertNode.get("MajorAlert").asInt();
                boolean isMajorAlert = isMajorAlertInt > 0;

                int TBDint = alertNode.get("TBD").asInt();
                boolean isTBD = TBDint > 0;
                String alertURL = alertNode.get("AlertURL").get("#cdata-section").asText();

                String impactedService = alertNode.get("ImpactedService").get("Service").get("ServiceId").asText();


                CTAAlert CTAAlert = new CTAAlert(id, headline, shortDesc, fullDesc, severity, eventStart, eventEnd, isTBD, isMajorAlert, alertURL, impactedService);
                CTAAlerts.add(CTAAlert);

            }
        }
            return  new CustomerAlertsResponse(errorCode,errorMsg,timeStamp, CTAAlerts.toArray(new CTAAlert[0]));
    }
}
