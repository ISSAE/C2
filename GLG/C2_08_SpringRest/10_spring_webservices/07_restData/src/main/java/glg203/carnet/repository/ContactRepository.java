package glg203.carnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import glg203.carnet.modele.Contact;
import glg203.carnet.modele.ContactProjection;

/**
 * ContactRepository
 */
@RepositoryRestResource(excerptProjection = ContactProjection.class)
public interface ContactRepository extends JpaRepository<Contact, Long>{

    
    List<Contact> findByNom(@Param("nom") String nom);

}