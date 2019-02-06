package alertsender;

import alertsender.config.DatasourceConfig;
import alertsender.config.ThymeleafConfig;
import alertsender.job.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:datasource.properties")
@PropertySource("classpath:template.properties")
@PropertySource("classpath:email.properties")
@PropertySource("classpath:traintrackerapi.properties")
@PropertySource("classpath:trainTrackerBatch.properties")
public class RunBatch  {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ThymeleafConfig.class);
        context.register(DatasourceConfig.class);
        context.register(SpringBatchConfig.class);
        context.register(CreateAlertNotificationsStep.class);
        context.register(RefreshStopsStep.class);
        context.register(SendEmailStep.class);
        context.register(CreateAlertNotificationsStep.class);
        context.register(TrainTrackerJob.class);


        context.refresh();

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("trainTracker");
        System.out.println("Starting the batch job");
        try {
            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Job Status : " + execution.getStatus());
            System.out.println("Job completed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Job failed");
        }



     }
}
