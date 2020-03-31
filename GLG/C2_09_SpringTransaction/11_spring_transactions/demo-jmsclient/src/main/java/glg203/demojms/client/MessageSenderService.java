package glg203.demojms.client;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import glg203.demojms.model.LogInfo;

@Service
@Transactional // Commentez cette ligne et voyez l'effet sur le serveur.
public class MessageSenderService {


	private JmsTemplate jmsTemplate;

	@Autowired
	public MessageSenderService(ConnectionFactory connectionFactory) {
		jmsTemplate = new JmsTemplate(connectionFactory);
        //jmsTemplate.setSessionTransacted(true);

	}
	
	/**
	 * Envoie deux messages.
	 * Si message2 est vide ou null, échoue en montrant le côté transactionnel de la chose.
	 * @param message1
	 * @param message2
	 */
	
	public void sendMessages(String message1, String message2) {
		jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
		jmsTemplate.convertAndSend("log", new LogInfo("INFO", message1));
		if (StringUtils.isEmpty(message2)) {
			throw new RuntimeException("échec");			
		}
		jmsTemplate.convertAndSend("log", new LogInfo("INFO", message2));		
	}

}
