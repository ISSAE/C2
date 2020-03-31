package glg203.jpa.repositories.custom;

import glg203.jpa.model.heritage.Document;

/**
 * Une personnalisation de DocumentRepository.
 * Nous définissons ici des méthodes pour lesquelles 
 * nous fourniront effectivement une implémentation "à la main"
 */
public interface DocumentRepositoryPersonnalise {

    /**
     * Retourne le document avec le plus grand id.
     * (il vaudrait mieux utiliser un timestamp, ceci dit)
     * @return
     */
    Document dernierDocument();

    
    
}