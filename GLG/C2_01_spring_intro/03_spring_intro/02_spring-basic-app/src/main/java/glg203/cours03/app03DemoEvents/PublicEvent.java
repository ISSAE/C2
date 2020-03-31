package glg203.cours03.app03DemoEvents;

import org.springframework.context.ApplicationEvent;

/**
 * PublicEvent
 */
public class PublicEvent extends ApplicationEvent{

    public PublicEvent(Object source) {
        super(source);
    }

    
    
}