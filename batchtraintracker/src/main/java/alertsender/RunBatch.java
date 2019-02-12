package alertsender;

//import alertsender.job.alertNotify.AlertNotifyJob;
//import alertsender.job.alertNotify.AlertNotifyJob;
//import alertsender.job.alertNotify.CreateAlertNotificationsStep;
//import alertsender.job.alertNotify.SendEmailStep;
//import alertsender.job.alertNotify.SpringBatchConfig;
//import alertsender.job.updateRefData.RefreshStopsStep;
//import alertsender.job.updateRefData.UpdateReferenceDataJob;
//import dao.config.DatasourceConfig;
//import alertsender.config.ThymeleafConfig;
//import alertsender.config.ThymeleafConfig;
//import alertsender.config.ThymeleafConfig;
//import alertsender.job.alertNotify.AlertNotifyJob;
//import alertsender.job.alertNotify.CreateAlertNotificationsStep;
//import alertsender.job.alertNotify.SendEmailStep;
//import alertsender.job.alertNotify.SpringBatchConfig;
//import alertsender.job.updateRefData.RefreshStopsStep;
//import alertsender.job.updateRefData.UpdateReferenceDataJob;
//import dao.config.DatasourceConfig;
//import alertsender.config.ThymeleafConfig;

import alertsender.config.ThymeleafConfig;
import alertsender.job.alertNotify.AlertNotifyJob;
import alertsender.job.alertNotify.CreateAlertNotificationsStep;
import alertsender.job.alertNotify.SendEmailStep;
import alertsender.job.alertNotify.SpringBatchConfig;
import alertsender.job.updateRefData.RefreshStopsStep;
import alertsender.job.updateRefData.UpdateReferenceDataJob;
import dao.config.DatasourceConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:datasource.properties")
@PropertySource("classpath:template.properties")
@PropertySource("classpath:email.properties")
@PropertySource("classpath:traintrackerapi.properties")
@PropertySource("classpath:trainTrackerBatch.properties")
//@SpringBootApplication
public class RunBatch implements ApplicationRunner {

//    public static void main(String[] args){
//
//
//
//     }
public static void main(String[] args)  throws Exception{
    SpringApplication.run(RunBatch.class, args);
}


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String jobName;
        JobParameters jobParameters = new JobParameters();
        if (args != null && args.getOptionNames().size() > 0){

            List<String> jobNameList = args.getOptionValues("jobName");
            if (jobNameList == null || jobNameList.isEmpty()) throw new IllegalArgumentException("Mandatory argument jobName not passed!");
            jobName = jobNameList.get(0);
//
//            JobParametersBuilder builder = new JobParametersBuilder();
//            for (String argname : args.getOptionNames()){
//                if (!argname.equalsIgnoreCase("jobName")) {
//                    builder.addString(argname, args.getOptionValues(argname).get(0));
//                }
//            }
//            jobParameters = builder.toJobParameters();
        }
        else throw new IllegalArgumentException("Job name must be specified");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ThymeleafConfig.class);
        context.register(DatasourceConfig.class);
        context.register(SpringBatchConfig.class);
        context.register(CreateAlertNotificationsStep.class);
        context.register(RefreshStopsStep.class);
        context.register(SendEmailStep.class);
        context.register(CreateAlertNotificationsStep.class);
        context.register(AlertNotifyJob.class);

        context.register(RefreshStopsStep.class);
        context.register(UpdateReferenceDataJob.class);


        context.refresh();

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean(jobName);
        System.out.println("Starting the batch job");
        try {
            JobExecution execution = jobLauncher.run(job, jobParameters);
            System.out.println("Job Status : " + execution.getStatus());
            System.out.println("Job completed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Job failed");
        }

    }
}
