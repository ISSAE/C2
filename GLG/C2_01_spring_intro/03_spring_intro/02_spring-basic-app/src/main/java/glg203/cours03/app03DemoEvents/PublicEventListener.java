package glg203.cours03.app03DemoEvents;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * PublicEventListener
 */
@Component
public class PublicEventListener //implements ApplicationListener<PublicEvent>
{

    @EventListener
    public void recevoirEvenement(PublicEvent event) {
        System.out.println("On a reçu "+ event);
    }

    
}