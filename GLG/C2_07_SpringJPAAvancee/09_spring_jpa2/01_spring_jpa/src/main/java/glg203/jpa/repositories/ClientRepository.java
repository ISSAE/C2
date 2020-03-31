package glg203.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import glg203.jpa.model.embed.Client;

/**
 * ClientRepository
 */
public interface ClientRepository extends JpaRepository<Client,Long>{

    
}