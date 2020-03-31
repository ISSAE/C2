package glg203.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import glg203.jpa.model.heritage.Document;
import glg203.jpa.repositories.custom.DocumentRepositoryPersonnalise;

/**
 * DocumentRepository.
 * 
 * <p>Ce repository est un peu particulier : nous montrons 
 * comment étendre un repository avec du "vrai" code java.
 * 
 * <p> Le principe est le suivant :
 * <ul>
 * <li> le repository, défini comme une interface, va étendre à la fois
 *   une des interface standard et une interface spécifique, qui définira
 * nos méthodes personnelles</li>
 * <li> 
 * <li>Une classe va implémenter l'interface spécifique (ici, <code>DocumentRepositoryPersonnalise</code>)
 * et elle seule. </li>
 * <li>Le nom de cette classe doit se terminer par <code>Impl</code> !!!</li>
 * <li>à l'intérieur de la classe, on peut utiliser l'injection de dépendance (même 
 * si elle n'est pas définie comme un composant Spring), et par exemple injecter l'entityManager 
 * avec 
 * <pre>
 *   @PersistenceContext
 *   EntityManager em;
 * </pre>
 * </ul>
 */
public interface DocumentRepository 
    extends JpaRepository<Document,Long>, DocumentRepositoryPersonnalise{

    
}