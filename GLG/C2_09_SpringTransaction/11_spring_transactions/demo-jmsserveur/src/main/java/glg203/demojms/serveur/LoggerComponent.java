package glg203.demojms.serveur;

import org.springframework.messaging.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import glg203.demojms.model.LogInfo;


/**
 * Un composant qui recevra les messages expédiés.
 */
@Component
@Transactional
public class LoggerComponent {

    @JmsListener(destination = "log")
    public void receiveInfo(Message<LogInfo> message) {                
        System.out.println("****************************************************************");
        System.out.println("* message reçu...");
        System.out.println("* ");
        System.out.println("* " + message.getHeaders());
        System.out.println("* " + message.getPayload());
        System.out.println("* ");
        System.out.println("****************************************************************");
        
    }

    
}