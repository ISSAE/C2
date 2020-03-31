package glg203.cours03.app03DemoEvents;

import org.springframework.context.ApplicationEvent;

/**
 * BoringEvent
 */
public class BoringEvent extends ApplicationEvent{

    public BoringEvent(Object source) {
        super(source);
    }

    
}