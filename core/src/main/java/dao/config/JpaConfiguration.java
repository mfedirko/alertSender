package dao.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = {"dao.impl", "dao"})
@EntityScan( basePackages = { "model.entity", "model"})
@Configuration
@EnableTransactionManagement
@Import(DatasourceConfig.class)
public class JpaConfiguration {


    @Value("${hbprop.hibernate.show_sql}") String showSql;
    @Value("${hbprop.hibernate.format_sql}") String formatSql;
    @Value("${hbprop.hibernate.hbm2ddl.auto}") String autoDDL;
    //    @Value("${hbprop.hibernate.transaction.flush_before_completion}") String flushTxnBeforeCompletion;


//    @Bean
//    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
//        HibernateJpaSessionFactoryBean ftry = new HibernateJpaSessionFactoryBean();
//        ftry.setEntityManagerFactory(emf);
//        return ftry;
//    }

//    @Bean
//    public PlatformTransactionManager jpaTransactionManager(DataSource dataSource){
//        JpaTransactionManager transactionManager
//                = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                entityManagerFactory(dataSource));
//        return transactionManager;
//    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("model.entity", "model");
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPersistenceUnitName("persistnc");

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.show_sql", showSql);
        jpaProperties.put("hibernate.format_sql", formatSql);
        jpaProperties.put("hibernate.hbm2ddl.auto",autoDDL);
        factoryBean.setJpaProperties(jpaProperties);
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();

    }


}
