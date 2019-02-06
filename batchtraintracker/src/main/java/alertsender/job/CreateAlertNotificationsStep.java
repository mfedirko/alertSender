package alertsender.job;

import alertsender.AlertPreferenceReader;
import alertsender.UserAlertProcessor;
import dao.AlertNotificationDAO;
import dao.CtaAlertPreferenceDAO;
import integration.cta.traintracker.client.TrainTrackerAlertsClient;
import model.entity.AlertNotification;
import model.entity.CtaAlertPreference;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Iterator;

@Configuration
public class CreateAlertNotificationsStep {

    @Autowired
    private CtaAlertPreferenceDAO ctaAlertPreferenceDAO;



    @Bean
    public ItemReader<CtaAlertPreference> ctaAlertPreferenceItemReader(){
        return new AlertPreferenceReader(ctaAlertPreferenceDAO);

    }
    @Bean
    public ItemProcessor<CtaAlertPreference, AlertNotification> ctaAlertProcessor(TrainTrackerAlertsClient client, AlertNotificationDAO dao){
        return new UserAlertProcessor(client,dao);
    }

    @Bean
    public ItemWriter<CtaAlertPreference> ctaAlertPreferenceItemWriter( EntityManagerFactory entityManagerFactory){
        return new JpaItemWriterBuilder<CtaAlertPreference>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
