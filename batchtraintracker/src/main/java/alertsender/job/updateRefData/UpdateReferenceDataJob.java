package alertsender.job.updateRefData;

//import integration.cta.traintracker.client.response.CtaStopResponse;
import integration.cta.traintracker.client.response.CtaStopResponse;
import model.entity.cta.customeralerts.CtaStop;
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
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class UpdateReferenceDataJob {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;




    @Bean
    protected Step refreshStops(ItemReader<CtaStopResponse> reader,
                         ItemProcessor<CtaStopResponse, CtaStop> processor,
                         ItemWriter<CtaStop> writer, PlatformTransactionManager txnMgr) {
        return steps.get("refreshStops").<CtaStopResponse, CtaStop> chunk(500)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionManager(txnMgr)
                .build();
    }

    @Bean(name = "referenceDataUpdater")
    public Job job(
            @Qualifier("refreshStops")  Step step1){
        return  jobs.get("referenceDataUpdater")
                .start(step1)
                .build();
    }
}
