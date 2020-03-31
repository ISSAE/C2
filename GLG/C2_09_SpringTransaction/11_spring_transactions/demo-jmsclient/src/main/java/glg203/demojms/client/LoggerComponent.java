package glg203.demojms.client;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;

import glg203.demojms.model.LogInfo;

/**
 * Un récepteur de message.
 * Dans cette application, dont le but est d'envoyer des messages, 
 * il est désactivé.
 */
//@Component
public class LoggerComponent {

    //@JmsListener(destination = "log")
    public void receiveInfo(Message<LogInfo> message) {
        System.out.println("*******************");
        System.out.println(message.getHeaders());
        System.out.println(message.getPayload());
    }

    
}