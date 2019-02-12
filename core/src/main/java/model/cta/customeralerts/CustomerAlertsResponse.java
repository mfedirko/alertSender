package model.cta.customeralerts;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = CustomerAlertsResponseDeserializer.class)
public class CustomerAlertsResponse {

    private CustAlertsError errorCode;

    public CustAlertsError getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(CustAlertsError errorCode) {
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

    public CTAAlert[] getCTAAlerts() {
        return CTAAlerts;
    }

    public CustomerAlertsResponse() {
    }

    public CustomerAlertsResponse(CustAlertsError errorCode, String errorMsg, Date timestamp, CTAAlert[] CTAAlerts) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.timestamp = timestamp;
        this.CTAAlerts = CTAAlerts;
    }

    @Override
    public String toString() {
        return "CustomerAlertsResponse{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", timestamp=" + timestamp +
                ", CTAAlerts=" + Arrays.toString(CTAAlerts) +
                '}';
    }

    public void setCTAAlerts(CTAAlert[] CTAAlerts) {
        this.CTAAlerts = CTAAlerts;
    }

    private String errorMsg;
    private Date timestamp;
    private CTAAlert[] CTAAlerts;


    public enum CustAlertsError {
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


        private static Map<Integer, CustAlertsError> codeMap = new HashMap<>();
        static {
            for (CustAlertsError errorCode1 : CustAlertsError.values()){
                codeMap.put(errorCode1.code,errorCode1);
            }
        }
        private int code;
        CustAlertsError(int code){
            this.code = code;
        }

        public static CustAlertsError fromNumber(int number){
            return codeMap.get(number);
        }


    }
}

