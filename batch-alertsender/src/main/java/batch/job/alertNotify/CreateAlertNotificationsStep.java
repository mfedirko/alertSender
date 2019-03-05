package batch.job.alertNotify;

//import batch.AlertPreferenceReader;
//import batch.UserAlertProcessor;
//import batch.UserAlertWriter;
//import dao.AlertDAO;
//import dao.AlertNotificationDAO;
//import dao.AlertPreferenceDAO;
//import CustomerAlertsClient;
//import TrainTrackerClient;
//import model.entity.api.AlertNotification;
//import model.entity.api.AlertPreference;
//import batch.AlertPreferenceReader;
//import batch.AlertPreferenceReader;

import batch.AlertPreferenceReader;
import batch.UserAlertProcessor;
import batch.UserAlertWriter;
import dao.AlertDAO;
import dao.AlertNotificationDAO;
import dao.AlertPreferenceDAO;
import integration.cta.traintracker.client.CustomerAlertsClient;
import integration.cta.traintracker.client.TrainTrackerClient;
import model.entity.api.AlertNotification;
import model.entity.api.AlertPreference;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;

//import dao.AlertDAO;
//import model.entity.api.AlertNotification;
//import model.entity.api.AlertNotification;

@Configuration
public class CreateAlertNotificationsStep {

    @Autowired
    private AlertPreferenceDAO ctaAlertPreferenceDAO;

    @Autowired
    private AlertNotificationDAO alertNotificationDAO;

    @Autowired
    private AlertDAO alertDAO;




    @Value("${traintracker.url.alerts}") private String alertsUrl;
    @Value("${traintracker.url.arrivals}") private String trainTrackerUrl;
    @Value("${traintracker.apiKey}") private String apiKey;

    @Bean
    public RestTemplate defaultRestTemplate(){
        return new RestTemplate();
    }


    @Bean
    public CustomerAlertsClient custAlertClient(RestTemplate restTemplate){
        return new CustomerAlertsClient(alertsUrl,restTemplate);
    }

    @Bean
    public TrainTrackerClient trainTrackerClient(RestTemplate restTemplate){
        return new TrainTrackerClient(restTemplate, trainTrackerUrl, apiKey);
    }


    @Bean
    public ItemReader<AlertPreference> ctaAlertPreferenceItemReader(){
        return new AlertPreferenceReader(ctaAlertPreferenceDAO);

    }
    @Bean
    public ItemProcessor<AlertPreference, AlertNotification> ctaAlertProcessor(RestTemplate restTemplate, AlertDAO dao){
        return new UserAlertProcessor(custAlertClient(restTemplate),
                                    trainTrackerClient(restTemplate),
                                    dao);
    }






    @Bean
    public ItemWriter<AlertNotification> ctaAlertPreferenceItemWriter(EntityManagerFactory entityManagerFactory) {
        return new UserAlertWriter();
//        return new HibernateItemWriterBuilder<AlertNotification>()
//                .sessionFactory(sessionFactory)
//                .clearSession(true)
//                .build();
//        return new JpaItemWriterBuilder<AlertNotification>()
//                .entityManagerFactory(entityManagerFactory)
//                .build();

    }
}
