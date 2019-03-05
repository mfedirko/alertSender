//package runner;
//
////import batch.job.alertNotify.AlertNotifyJob;
////import batch.job.alertNotify.AlertNotifyJob;
////import batch.job.alertNotify.CreateAlertNotificationsStep;
////import batch.job.alertNotify.SendEmailStep;
////import config.SpringBatchConfig;
////import batch.job.updateRefData.RefreshStopsStep;
////import batch.job.updateRefData.UpdateReferenceDataJob;
////import dao.config.DatasourceConfig;
////import batch.config.ThymeleafConfig;
////import batch.config.ThymeleafConfig;
////import batch.config.ThymeleafConfig;
////import batch.job.alertNotify.AlertNotifyJob;
////import batch.job.alertNotify.CreateAlertNotificationsStep;
////import batch.job.alertNotify.SendEmailStep;
////import config.SpringBatchConfig;
////import batch.job.updateRefData.RefreshStopsStep;
////import batch.job.updateRefData.UpdateReferenceDataJob;
////import dao.config.DatasourceConfig;
////import batch.config.ThymeleafConfig;
//
//import config.SpringBatchConfig;
//import config.ThymeleafConfig;
//
//import dao.config.DatasourceConfig;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import java.util.List;
//
//public abstract class RunBatch implements ApplicationRunner {
//
////    public static void main(String[] args){
////
////
////
////     }
//public static void main(String[] args)  throws Exception{
//    SpringApplication.run(RunBatch.class, args);
//}
//
//public abstract AnnotationConfigApplicationContext getContext();
//
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        String jobName;
//        JobParameters jobParameters = new JobParameters();
//        if (args != null && args.getOptionNames().size() > 0){
//
//            List<String> jobNameList = args.getOptionValues("jobName");
//            if (jobNameList == null || jobNameList.isEmpty()) throw new IllegalArgumentException("Mandatory argument jobName not passed!");
//            jobName = jobNameList.get(0);
////
////            JobParametersBuilder builder = new JobParametersBuilder();
////            for (String argname : args.getOptionNames()){
////                if (!argname.equalsIgnoreCase("jobName")) {
////                    builder.addString(argname, args.getOptionValues(argname).get(0));
////                }
////            }
////            jobParameters = builder.toJobParameters();
//        }
//        else throw new IllegalArgumentException("Job name must be specified");
//        AnnotationConfigApplicationContext context = getContext();
//        context.refresh();
//
//        JobLauncher jobLauncher = (JobLauncher) context.getBean("myJobLauncher");
//        Job job = (Job) context.getBean(jobName);
//        System.out.println("Starting the batch job");
//        try {
//            JobExecution execution = jobLauncher.run(job, jobParameters);
//            System.out.println("Job Status : " + execution.getStatus());
//            System.out.println("Job completed");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Job failed");
//        }
//
//    }
//}
