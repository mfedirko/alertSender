package model.cta.customeralerts;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import model.entity.TrainTrackerArrival;

import java.util.*;

@JsonDeserialize(using = TrainTrackerArrivalResponseDeserializer.class)
public class TrainTrackerArrivalResponse {

    private TrainTrackerError errorCode;

    private Date timestamp;

    private String errorMsg;

    private List<TrainTrackerArrival> trainTrackerArrivalList = new ArrayList<>();

    public TrainTrackerError getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(TrainTrackerError errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<TrainTrackerArrival> getTrainTrackerArrivalList() {
        return trainTrackerArrivalList;
    }

    public void setTrainTrackerArrivalList(List<TrainTrackerArrival> trainTrackerArrivalList) {
        this.trainTrackerArrivalList = trainTrackerArrivalList;
    }

    @Override
    public String toString() {
        return "TrainTrackerArrivalResponse{" +
                "errorCode=" + errorCode +
                ", timestamp=" + timestamp +
                ", errorMsg='" + errorMsg + '\'' +
                ", trainTrackerArrivalList=" + trainTrackerArrivalList +
                '}';
    }

    public enum TrainTrackerError {
        NO_ERROR (0),
        PARAM_MISSING(100),
        INVALID_API_KEY(101),
        MAX_USAGE_EXCEEDED(102),
        BAD_MAPID(103),
        BAD_MAPID2(104),
        BAD_MAPID3(105),
        BAD_ROUTEID(106),
        BAD_ROUTEID2(107),
        BAD_STPID(108),
        BAD_STPID2(109),
        BAD_STPID3(112),
        BAD_MAX(110),
        BAD_MAX2(111),
        PARAM_INVALID(500),
        SERVER_ERROR(900);


        private static Map<Integer, TrainTrackerError> codeMap = new HashMap<>();
        static {
            for (TrainTrackerError errorCode1 :TrainTrackerError.values()){
                codeMap.put(errorCode1.code,errorCode1);
            }
        }
        private int code;
        TrainTrackerError(int code){
            this.code = code;
        }

        public static TrainTrackerError fromNumber(int number){
            return codeMap.get(number);
        }
    }
}
