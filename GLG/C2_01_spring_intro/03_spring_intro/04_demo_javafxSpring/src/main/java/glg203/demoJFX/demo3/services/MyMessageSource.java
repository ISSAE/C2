package glg203.demoJFX.demo3.services;

import org.springframework.stereotype.Service;

/**
 * Une implémentation particulière de IMessageSource qui sera utilisée par Spring.
 */
@Service
public class MyMessageSource implements IMessageSource {

    @Override
    public String getMessage() {
        return "Version Spring-ifiée de l'application.";
    }
    
}