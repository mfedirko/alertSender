package alertsender.dto;


import model.entity.User;
import model.Alert;

public class UserAlertDTO {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAlertDTO() {
    }

    public Alert[] getAlerts() {
        return alerts;
    }

    public void setAlerts(Alert[] alerts) {
        this.alerts = alerts;
    }

    private Alert[] alerts;

    public UserAlertDTO(User user, Alert[] alerts) {
        this.user = user;
        this.alerts = alerts;
    }
}
