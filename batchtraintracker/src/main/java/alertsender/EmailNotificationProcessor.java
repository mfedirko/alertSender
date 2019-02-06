package alertsender;

import com.example.service.email.service.EmailService;
import com.example.service.email.util.TemplateHelper;
import model.entity.AlertNotification;
import model.entity.DefaultEmail;
import model.Email;
import model.TemplateConstant;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmailNotificationProcessor implements ItemProcessor<AlertNotification,AlertNotification> {

    private String REPLY_TO;
    private String FROM;
    private String SUBJECT;

    private TemplateHelper templateHelper;
    private EmailService service;

    public EmailNotificationProcessor(TemplateHelper templateHelper, EmailService emailService){
        this.templateHelper = templateHelper;
        this.service = emailService;
    }

    public void setREPLY_TO(String REPLY_TO) {
        this.REPLY_TO = REPLY_TO;
    }

    public void setFROM(String FROM) {
        this.FROM = FROM;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }



    @Override
    public AlertNotification process(AlertNotification alertNotification) throws Exception {
        if (alertNotification == null ||
            alertNotification.getPreference() == null ||
            alertNotification.getPreference().getUser() == null){
            return null;
        }

        Email email = DefaultEmail.builder()
                .body(buildEmailFromTemplate(alertNotification))
                .isHTML(true)
                .replyTo(REPLY_TO)
                .from(FROM)
                .to(new String[]{alertNotification.getPreference().getUser().getEmail()})
                .subject(SUBJECT)
                .build();

        boolean isSuccess = false;
        try {
            isSuccess = service.sendEmail(email);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        if (isSuccess)
            alertNotification.setProcessingStatus(AlertNotification.Status.SENT);
        else
            alertNotification.setProcessingStatus(AlertNotification.Status.FAILED);

        return alertNotification;

    }

    private String buildEmailFromTemplate(AlertNotification alertNotification) {
        Map<String,Object> templateParams = new HashMap<>();
        templateParams.put("alerts",alertNotification.getAlerts());
        templateParams.put("receivedTime", new Date());
        String emailBody = templateHelper.createBodyFromTemplate(TemplateConstant.ALERT_NOTIFICATION, templateParams);
        return emailBody;
    }

}
