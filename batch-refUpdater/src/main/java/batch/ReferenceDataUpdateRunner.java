package batch;

import batch.job.updateRefData.RefreshStopsStep;
import batch.job.updateRefData.UpdateReferenceDataJob;
import config.SpringBatchConfig;
import config.ThymeleafConfig;
import dao.config.DatasourceConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@PropertySource("classpath:datasource.properties")
@PropertySource("classpath:template.properties")
@PropertySource("classpath:email.properties")
@PropertySource("classpath:traintrackerapi.properties")
public class ReferenceDataUpdateRunner implements CommandLineRunner {

    @Autowired
    private JobRepository jobRepository;

    @Resource(name = "referenceDataUpdater")
    private Job job;

    public static void main(String[] args) {
        SpringApplication.run(ReferenceDataUpdateRunner.class, args);
    }

    private JobParameters getParameters(String ... args){
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        if (args.length > 0){
            List<String> argsList = Arrays.asList(args);
            //TODO handle args
        }
        return builder.toJobParameters();
    }

    @Override
    public void run(String... args) throws Exception {
            SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
            jobLauncher.setJobRepository(jobRepository);
            jobLauncher.afterPropertiesSet();
            jobLauncher.run(job,getParameters());
    }
}
