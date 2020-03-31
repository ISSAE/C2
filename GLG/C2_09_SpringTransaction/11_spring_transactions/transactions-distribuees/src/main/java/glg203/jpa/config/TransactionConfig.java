package glg203.jpa.config;

import java.util.Properties;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * TransactionConfig.
 * Configuration des transactions.
 * 
 * Inspiré de l'exemple fourni avec atomikos, ainsi que de Pro Spring, 5e
 * édition.
 * 
 * <p> Autre source possible :  http://fabiomaffioletti.me/blog/2014/04/15/distributed-transactions-multiple-databases-spring-boot-spring-data-jpa-atomikos/
 * (mais l'ajout de
 * <pre>jpaProperties.put("hibernate.transaction.coordinator_class", "jta");</pre>
 * dans la configuration des entity manager {@link Banque1Config#emf1()} et  {@link Banque2Config#emf2()} 
 * simplifie les choses.
 */
@Configuration
public class TransactionConfig {

    @Bean(initMethod = "init", destroyMethod = "shutdownForce")
    public UserTransactionService userTransactionService() {
        Properties props = new Properties();
        props.put("com.atomikos.icatch.service", "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
        return new UserTransactionServiceImp(props);
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager userTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(10000);
        userTransactionManager.setForceShutdown(true);
        userTransactionManager.setStartupTransactionService(true);
        return userTransactionManager;
    }

    @Bean
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp ut = new UserTransactionImp();
        ut.setTransactionTimeout(300);
        return ut;
    }

    @Bean(name = "transactionManager")
    public JtaTransactionManager transactionManager() throws SystemException {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager()); // ref="userTransactionManager"
        jtaTransactionManager.setUserTransaction(userTransaction()); // ref="userTransactionManager"
        return jtaTransactionManager;
    }

}