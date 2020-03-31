package glg203.carnet.modele;

import org.springframework.data.rest.core.config.Projection;

/**
 * EntreeP
 */
@Projection(name = "entreeP", types = {Entree.class})
public interface EntreeP {
     TypeEntree getTypeEntree();
     String getValeur();
}