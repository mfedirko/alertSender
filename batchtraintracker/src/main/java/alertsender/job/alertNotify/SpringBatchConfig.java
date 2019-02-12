package alertsender.job.alertNotify;

//import dao.config.TrainTrackerJpaConfiguration;
//import dao.config.TrainTrackerJpaConfiguration;
//import dao.config.TrainTrackerJpaConfiguration;
import dao.config.TrainTrackerJpaConfiguration;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.net.MalformedURLException;

@Configuration
@Import(TrainTrackerJpaConfiguration.class)
@EnableBatchProcessing
public class SpringBatchConfig {

    @Value("org/springframework/batch/core/schema-drop-mysql.sql")
    private Resource dropReopsitoryTables;

    @Value("org/springframework/batch/core/schema-mysql.sql")
    private Resource dataReopsitorySchema;


    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource)
            throws MalformedURLException {
        ResourceDatabasePopulator databasePopulator =
                new ResourceDatabasePopulator();

        databasePopulator.addScript(dropReopsitoryTables);
        databasePopulator.addScript(dataReopsitorySchema);
        databasePopulator.setIgnoreFailedDrops(true);

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator);

        return initializer;
    }

    private JobRepository getJobRepository(DataSource dataSource) throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(getTransactionManager());
        factory.afterPropertiesSet();
        return (JobRepository) factory.getObject();
    }

    private PlatformTransactionManager getTransactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean(name = "jobLauncher")
    public JobLauncher getJobLauncher(DataSource dataSource) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(getJobRepository(dataSource));
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
}

