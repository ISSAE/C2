package glg203.jpa.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import glg203.jpa.repositories.banque2.Banque2Repository;

/**
 * Configuration de la base de données Banque2.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = Banque2Repository.class, entityManagerFactoryRef = "emf2", transactionManagerRef = "transactionManager")
@DependsOn("transactionManager")
public class Banque2Config {

    @DependsOn(value = { "DBStarterComponent" })
    @Bean(/*initMethod = "init", destroyMethod = "close"*/)
    public DataSource db2DataSource() {      
        return ConfigHelper.creerDataSource("db2", "50034");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf2() {
        return ConfigHelper.creerEntityManagerFactory(db2DataSource());
    }

}