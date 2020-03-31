package glg203.cours03.app03DemoEvents;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Logiciel : un "faux" composant, qui va publier des événements.
 */
@Component
public class Logiciel {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    

    @PostConstruct
    public void apresInit() {
        System.out.println("Le logiciel est prêt");
    }


    @PreDestroy
    public void avantDestruction() {
        System.out.println("Le logiciel va être détruit");
    }

    public void run() {
        System.out.println("On commence l'application");
        applicationEventPublisher.publishEvent(new PublicEvent(this));
        for (int i= 0; i < 10; i++) {
            applicationEventPublisher.publishEvent(new BoringEvent(this));
        }
        applicationEventPublisher.publishEvent(new PublicEvent(this));
        System.out.println("On arrête l'application");
    }
    
}