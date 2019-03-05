package batch.job.alertNotify;

//import integration.cta.traintracker.client.response.CtaStopResponse;
//import model.entity.api.AlertNotification;
//import model.entity.api.AlertPreference;
//import model.entity.cta.customeralerts.CtaAlertPreference;
//import model.entity.cta.customeralerts.CtaStop;

import config.SpringBatchConfig;
import model.entity.api.AlertNotification;
import model.entity.api.AlertPreference;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Import(SpringBatchConfig.class)
public class AlertNotifyJob {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;



    @Bean
    protected Step findAlerts(ItemReader<AlertPreference> reader,
                         ItemProcessor<AlertPreference, AlertNotification> processor,
                         @Qualifier("ctaAlertPreferenceItemWriter") ItemWriter<AlertNotification> writer, PlatformTransactionManager txnMgr) {
        return steps.get("findAlerts").<AlertPreference, AlertNotification> chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionManager(txnMgr)
                .build();
    }

    @Bean
    protected Step sendNotifications(ItemReader<AlertNotification> reader,
                         ItemProcessor<AlertNotification, AlertNotification> processor,
                         @Qualifier("alertNotificationItemWriter") ItemWriter<AlertNotification> writer, PlatformTransactionManager txnMgr) {
        return steps.get("sendNotifications").<AlertNotification, AlertNotification> chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionManager(txnMgr)
                .build();
    }


    @Bean(name = "alertNotifier")
    public Job job(
                   @Qualifier("findAlerts")  Step step2,
                   @Qualifier("sendNotifications") Step  step3){
        return  jobs.get("alertNotifyJob")
                .start(step2)
                .next(step3)
                .build();
    }

}
