package model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TrainTrackerAlertResponse {

    private TrainTrackerAlertError errorCode;

    public TrainTrackerAlertError getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(TrainTrackerAlertError errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public TrainTrackerAlert[] getTrainTrackerAlerts() {
        return trainTrackerAlerts;
    }

    public TrainTrackerAlertResponse() {
    }

    public TrainTrackerAlertResponse(TrainTrackerAlertError errorCode, String errorMsg, Date timestamp, TrainTrackerAlert[] trainTrackerAlerts) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.timestamp = timestamp;
        this.trainTrackerAlerts = trainTrackerAlerts;
    }

    @Override
    public String toString() {
        return "TrainTrackerAlertResponse{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", timestamp=" + timestamp +
                ", trainTrackerAlerts=" + Arrays.toString(trainTrackerAlerts) +
                '}';
    }

    public void setTrainTrackerAlerts(TrainTrackerAlert[] trainTrackerAlerts) {
        this.trainTrackerAlerts = trainTrackerAlerts;
    }

    private String errorMsg;
    private Date timestamp;
    private TrainTrackerAlert[] trainTrackerAlerts;


    public enum TrainTrackerAlertError {
        NO_ERROR(0),
        NO_ACTIVE_ALERTS(25),
        NO_MATCHING_ALERTS(50),
        INVALID_OPTION_ACTIVEONLY(100),
        INVALID_OPTION_ACCESSIBILITY(101),
        INVALID_OPTION_PLANNED(102),
        INVALID_OPTION_STATIONID(103),
        INVALID_OPTION_BYSTARTDATE(104),
        INVALID_OPTION_RECENTDAYS(105),
        INVALID_OPTIONS_RECENTDAYS_ROUTEID(106),
        INVALID_OPTIONS_RECENTDAYS_BYSTARTDATE(107),
        INVALID_PARAM(500),
        SERVER_ERROR(900);


        private static Map<Integer, TrainTrackerAlertError> codeMap = new HashMap<>();
        static {
            for (TrainTrackerAlertError errorCode1 : TrainTrackerAlertError.values()){
                codeMap.put(errorCode1.code,errorCode1);
            }
        }
        private int code;
        TrainTrackerAlertError(int code){
            this.code = code;
        }

        public static TrainTrackerAlertError fromNumber(int number){
            return codeMap.get(number);
        }


    }
}

