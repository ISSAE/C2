package lb.edu.isae.sessions;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Compteur : exemple basique de bean session...
 */

@Component
@SessionScope
public class Compteur {

    private final AtomicInteger compteur= new AtomicInteger();

    public int getValeur() {
        return compteur.incrementAndGet();
    }

    public int incrementeValeur(int n) {
        return compteur.addAndGet(n);
    }
}