package glg203.demojms.serveur;

import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJms
//@EnableTransactionManagement
public class DemoReceiver {

	@Value("${spring.activemq.broker-url}")
	String brokerUrl;


/*	@Bean
	public JmsTransactionManager TransactionManager() {
		return new JmsTransactionManager(amqConnectionFactory());
	}*/

	@Bean
	public ActiveMQConnectionFactory amqConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
		factory.setTrustedPackages(Arrays.asList("glg203.demojms.model"));
		factory.setPassword("test");
		factory.setUserName("test");
		return factory;
	}

	@Bean
	public BrokerService broker() throws Exception {
		BrokerService broker = new BrokerService();
		broker.addConnector(brokerUrl);
		broker.setPersistent(false);
		AuthenticationUser user = new AuthenticationUser("test", "test", "");
		BrokerPlugin[] plugins = {
			new SimpleAuthenticationPlugin(Arrays.asList(user))
		};
		broker.setPlugins(plugins);
        return broker;
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoReceiver.class, args);
	}
}
