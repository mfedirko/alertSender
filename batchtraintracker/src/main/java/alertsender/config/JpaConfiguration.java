package alertsender.config;

import org.hibernate.Metamodel;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@EnableJpaRepositories(basePackages = {"dao.impl","dao"})
//@EntityScan( basePackages = { "model.entity", "model"})
@Configuration
@Import(DatasourceConfig.class)
public class JpaConfiguration {




}
