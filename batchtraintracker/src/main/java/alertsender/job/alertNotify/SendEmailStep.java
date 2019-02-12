package alertsender.job.alertNotify;

//import alertsender.AlertNotificationReader;
//import alertsender.EmailNotificationProcessor;
//import EmailService;
//import EmailServiceImpl;
//import TemplateHelper;
//import dao.AlertNotificationDAO;
//import EmailClient;
//import EmailSenderImpl;
//import EmailSenderConfig;
//import model.TemplateConstant;
//import model.entity.api.AlertNotification;

import alertsender.AlertNotificationReader;
import alertsender.EmailNotificationProcessor;
import com.example.service.email.service.EmailService;
import com.example.service.email.service.EmailServiceImpl;
import com.example.service.email.util.TemplateHelper;
import dao.AlertNotificationDAO;
import integration.email.EmailClient;
import integration.email.EmailSenderImpl;
import integration.email.config.EmailSenderConfig;
import model.TemplateConstant;
import model.entity.api.AlertNotification;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Import(EmailSenderConfig.class)
@PropertySource("classpath:trainTrackerBatch.properties")
public class SendEmailStep {

    @Autowired
    private AlertNotificationDAO alertNotificationDAO;


    @Value("${traintracker.batch.subject}") private String EMAIL_SUBJECT;
    @Value("${traintracker.batch.from}") private String EMAIL_FROM;
    @Value("${traintracker.batch.replyTo}") private String EMAIL_REPLY_TO;

    public void setEMAIL_SUBJECT(String EMAIL_SUBJECT) {
        this.EMAIL_SUBJECT = EMAIL_SUBJECT;
    }

    public void setEMAIL_FROM(String EMAIL_FROM) {
        this.EMAIL_FROM = EMAIL_FROM;
    }

    public void setEMAIL_REPLY_TO(String EMAIL_REPLY_TO) {
        this.EMAIL_REPLY_TO = EMAIL_REPLY_TO;
    }

    @Bean
    public EmailClient emailClient(JavaMailSender javaMail){
        return new EmailSenderImpl(javaMail);
    }
    @Bean
    public EmailService emailService(JavaMailSender javaMailSender){
        return new EmailServiceImpl(emailClient(javaMailSender));
    }

    @Bean
    public TemplateHelper templateHelper(TemplateEngine templateEngine){
        TemplateHelper helper = new TemplateHelper(templateEngine);
        Map<TemplateConstant,String> templateLocations = new HashMap<>();
        templateLocations.put(TemplateConstant.ALERT_NOTIFICATION,"alertNotification");
        helper.setTemplateLocations(templateLocations);
        return helper;
    }

    @Bean
    public ItemReader<AlertNotification> alertNotificationItemReader(){
        return new AlertNotificationReader(alertNotificationDAO);
    }
    @Bean
    public ItemProcessor<AlertNotification,AlertNotification>alertNotificationItemProcessor(TemplateHelper templateHelper, JavaMailSender javaMailSender){
        EmailNotificationProcessor processor = new EmailNotificationProcessor(templateHelper,emailService(javaMailSender));
        processor.setFROM(EMAIL_FROM);
        processor.setREPLY_TO(EMAIL_REPLY_TO);
        processor.setSUBJECT(EMAIL_SUBJECT);
        return processor;
    }

    @Bean
    public ItemWriter<AlertNotification> alertNotificationItemWriter(EntityManagerFactory entityManagerFactory){
        return new JpaItemWriterBuilder<AlertNotification>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


}
