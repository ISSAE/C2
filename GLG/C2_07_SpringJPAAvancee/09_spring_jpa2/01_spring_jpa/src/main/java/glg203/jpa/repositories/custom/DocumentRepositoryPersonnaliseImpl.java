package glg203.jpa.repositories.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import glg203.jpa.model.heritage.Document;

/**
 * Implémentation de l'interface  DocumentRepositoryPersonnalise.
 * Le suffixe <code>Impl</code> est <b>important</b> pour Spring.
 */
public class DocumentRepositoryPersonnaliseImpl implements DocumentRepositoryPersonnalise {

    @PersistenceContext
    EntityManager em;

    @Override
    public Document dernierDocument() {
        // Cette méthode n'a pas vraiment besoin d'être dans 
        // une classe spécifique, on pourrait utiliser une simple
        // annotation @Query. 
        // Mais c'est juste pour donner un exemple.
        return em.createQuery("select d from Document d where d.id = (select max(doc.id) from Document doc)", Document.class).getSingleResult();
    }

    
}