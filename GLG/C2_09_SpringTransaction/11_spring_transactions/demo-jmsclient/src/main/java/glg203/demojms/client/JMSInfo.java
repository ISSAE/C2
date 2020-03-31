package glg203.demojms.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Pour être sûr que les valeurs injectées depuis application.properties soient 
 * fixées lors de la création des beans, nous avons déplacé celles-ci...
 * @author rosmord
 *
 */
@Component
public class JMSInfo {
	

	@Value("${spring.activemq.broker-url}")
	String jmsUrl;

	@Value("${spring.activemq.user}")
	String login;

	@Value("${spring.activemq.password}")
	String password;

	public String getJmsUrl() {
		return jmsUrl;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	
}
