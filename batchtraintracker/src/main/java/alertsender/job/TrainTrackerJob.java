package alertsender.job;

import model.CtaStopResponse;
import model.entity.AlertNotification;
import model.entity.CtaAlertPreference;
import model.entity.CtaStop;
import model.entity.CtaTrainRoute;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class TrainTrackerJob {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;


    @Bean
    protected Step step1(ItemReader<CtaStopResponse> reader,
                         ItemProcessor<CtaStopResponse, CtaStop> processor,
                         ItemWriter<CtaStop> writer) {
        return steps.get("step1").<CtaStopResponse, CtaStop> chunk(10)
                .reader(reader).processor(processor).writer(writer).build();
    }

    @Bean
    protected Step step2(ItemReader<CtaAlertPreference> reader,
                         ItemProcessor<CtaAlertPreference, AlertNotification> processor,
                         ItemWriter<AlertNotification> writer) {
        return steps.get("step2").<CtaAlertPreference, AlertNotification> chunk(10)
                .reader(reader).processor(processor).writer(writer).build();
    }

    @Bean
    protected Step step3(ItemReader<AlertNotification> reader,
                         ItemProcessor<AlertNotification, AlertNotification> processor,
                         ItemWriter<AlertNotification> writer) {
        return steps.get("step3").<AlertNotification, AlertNotification> chunk(10)
                .reader(reader).processor(processor).writer(writer).build();
    }


    @Bean(name = "trainTracker")
    public Job job(@Qualifier("step1") Step step1,
                   @Qualifier("step2")  Step step2,
                   @Qualifier("step3") Step  step3){
        return  jobs.get("trainTrackerJob")
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

}
