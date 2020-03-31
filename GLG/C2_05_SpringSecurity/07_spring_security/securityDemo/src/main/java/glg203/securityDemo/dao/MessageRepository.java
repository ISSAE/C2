package glg203.securityDemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import glg203.securityDemo.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{
      /**
     * Récupère tous les messages avec leur auteur.
     */
    @Query("select distinct m from Message m left join fetch m.auteur")
    List<Message> findAllWithAuteur();

    List<Message> findByTitre(String titre);
}