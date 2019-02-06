package alertsender.job;

import alertsender.EmailNotificationProcessor;
import com.example.service.email.service.EmailService;
import com.example.service.email.util.TemplateHelper;
import dao.AlertNotificationDAO;
import model.TemplateConstant;
import model.entity.AlertNotification;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.HibernateCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.thymeleaf.TemplateEngine;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SendEmailStep {

    @Bean
    public TemplateHelper templateHelper(TemplateEngine templateEngine){
        TemplateHelper helper = new TemplateHelper(templateEngine);
        Map<TemplateConstant,String> templateLocations = new HashMap<>();
        templateLocations.put(TemplateConstant.ALERT_NOTIFICATION,"alertNotification");
        helper.setTemplateLocations(templateLocations);
        return helper;
    }

    @Bean
    public ItemReader<AlertNotification> alertNotificationItemReader(EntityManagerFactory entityManagerFactory, SessionFactory sessionFactory){
        return new HibernateCursorItemReaderBuilder<AlertNotification>()
                .entityClass(AlertNotification.class)
                .sessionFactory(sessionFactory)
                .queryString("SELECT a from AlertNotification WHERE a.processingStatus = Status.NEW")
                .useStatelessSession(true)
                .build();
    }
    @Bean
    public ItemProcessor<AlertNotification,AlertNotification>alertNotificationItemProcessor(TemplateHelper templateHelper, EmailService emailService){
        EmailNotificationProcessor processor = new EmailNotificationProcessor(templateHelper,emailService);
        processor.setFROM("${traintracker.batch.from}");
        processor.setREPLY_TO("${traintracker.batch.replyTo}");
        processor.setSUBJECT("${traintracker.batch.subject}");
        return processor;
    }

    @Bean
    public ItemWriter<AlertNotification> alertNotificationItemWriter(EntityManagerFactory entityManagerFactory){
        return new JpaItemWriterBuilder<AlertNotification>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }


}
