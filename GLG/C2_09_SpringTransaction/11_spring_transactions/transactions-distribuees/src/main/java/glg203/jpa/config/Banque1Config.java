package glg203.jpa.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import glg203.jpa.repositories.banque1.Banque1Repository;

/**
 * Configuration de la base de données Banque1.
 */
@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
    basePackageClasses = Banque1Repository.class, 
    entityManagerFactoryRef = "emf1", 
    transactionManagerRef = "transactionManager")
public class Banque1Config {

    @DependsOn(value = { "DBStarterComponent" })
    @Bean(/*initMethod = "init", destroyMethod = "close"*/)
    public DataSource db1DataSource() {
       return ConfigHelper.creerDataSource("db1", "50033");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf1() {
        return ConfigHelper.creerEntityManagerFactory(db1DataSource());
    }

}