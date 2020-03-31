package glg203.demojms.client;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.TransactionManager;

// Pour la configuration de la connectionfactory, voir https://singztechmusings.wordpress.com/2011/04/24/problem-with-creating-jms-messageproducer-using-spring-jmstemplate-how-to-solve/
@Configuration
public class Config {

	// @Autowired
	// JMSInfo jmsInfo;

	@Value("${spring.activemq.broker-url}")
	String jmsUrl;

	@Value("${spring.activemq.user}")
	String login;

	@Value("${spring.activemq.password}")
	String password;

	@Bean	
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(login, password, jmsUrl);
		return connectionFactory;
	}

	/**
	 * Ce bean n'est pas nécessaire.
	 * Il fournit cependant la possibilité de conserver
	 * les messages qui n'ont pas encore atteint le serveur.
	 * Et donc de gérer le cas où le serveur ne tourne pas.
	 * @return
	 */
	@Bean
	@Primary // Parce qu'il y a deux ConnectionFactory
	public CachingConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(activeMQConnectionFactory());
		// cachingConnectionFactory.afterPropertiesSet();
		cachingConnectionFactory.setSessionCacheSize(10);
		return cachingConnectionFactory;
	}

	@Bean
	public TransactionManager transactionManager(ConnectionFactory connectionFactory) {
		return new JmsTransactionManager(connectionFactory);
	}

}
