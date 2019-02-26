package alertsender.job.updateRefData;

import alertsender.CtaStopRESTReader;
import alertsender.CtaStopResponseProcessor;
import integration.cta.traintracker.client.response.CtaStopResponse;
import model.entity.cta.customeralerts.CtaStop;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;

@Configuration
@PropertySource("classpath:traintrackerapi.properties")
public class RefreshStopsStep {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public ItemProcessor<CtaStopResponse, CtaStop> ctaStopResponseProcessor(){
        return new CtaStopResponseProcessor();
    }
    @Bean
    public ItemReader<CtaStopResponse> ctaStopReader(@Value("${ref.stops.url}") String apiURL, RestTemplate restTemplate){
        return new CtaStopRESTReader(apiURL,restTemplate);
    }
    @Bean
    public ItemWriter<CtaStop> ctaStopItemWriter(EntityManagerFactory entityManagerFactory){
        return new JpaItemWriterBuilder<CtaStop>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
