package model;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import model.entity.CtaTrainRoute;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CtaStopResponseDeserializer extends JsonDeserializer<CtaStopResponse> {
    //,{":@computed_region_6mkv_f3dw":"26618","ada":false,"blue":true,"brn":false,"direction_id":"W","g":false,"location":{"type":"Point","coordinates":[-87.791602,41.872108]},"map_id":"40180","o":false,"p":false,"pexp":false,"pnk":false,"red":false,"station_descriptive_name":"Oak Park (Blue Line)","station_name":"Oak Park","stop_id":"30035","stop_name":"Oak Park (Forest Pk-bound)","y":false}



    @Override
    public CtaStopResponse deserialize(JsonParser jsonParser,
                                       DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Direction direction = Direction.valueOf(node.get("direction_id").asText());

        int mapId = node.get("map_id").asInt();
        int stopId = node.get("stop_id").asInt();
        String stopName = node.get("stop_name").asText();
        Pair<Double, Double> coords = null;
        List<Double> coordsList = new ArrayList<Double>();
        Iterator<JsonNode> itCoord = node.get("location").get("coordinates").elements();
        while (   itCoord.hasNext()   ){
            coordsList.add( itCoord.next().doubleValue());
        }
        if (coordsList.size() == 2) coords = Pair.of(coordsList.get(0), coordsList.get(1));
        CtaTrainRoute route = null;
        Set<String> routeIds= CtaTrainRoute.getRouteIdMap().keySet();
        for ( String routeId : routeIds){
            if (node.get(routeId).booleanValue()){
                route = CtaTrainRoute.fromRouteId(routeId);
                break;
            }
        }

        return new CtaStopResponse( stopId, mapId, coords, direction, stopName , route);
    }


}
