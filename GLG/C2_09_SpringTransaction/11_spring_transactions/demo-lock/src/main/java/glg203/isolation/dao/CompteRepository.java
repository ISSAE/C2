package glg203.isolation.dao;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import glg203.isolation.model.Compte;
import glg203.isolation.model.CompteReadOnly;

/**
 * CompteRepository.
 */
public interface CompteRepository extends CrudRepository<Compte,Long> {

    // Note : @QueryHints est supposé fixer des paramètres envoyés lors de la requête. Ici,
    // fixer le lock timeout... Ne semble pas fonctionner correctement avec h2.
    // Actuellement, le timeout est codé en dur dans l'URL de la base : 
    //                 jdbc:h2:mem:mydb;DB_CLOSE_ON_EXIT=false;DEFAULT_LOCK_TIMEOUT=120000

    // Pour le type de retour CompteReadOnly, voir la documentation de celui-ci.
    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    @Query("select c from Compte c")
    List<CompteReadOnly> findAsReadable();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    //@QueryHints({@QueryHint(name = "org.hibernate.timeout"   , value = "100000")})
    @Query("select c from Compte c")
    List<Compte> findAsWriteable();

}