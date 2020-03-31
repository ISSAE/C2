package glg203.jpa.repositories.banque1;

// VOIR https://dzone.com/articles/multiple-databases-with-shared-entity-classes-in-s
import org.springframework.stereotype.Repository;

import glg203.jpa.model.Compte;

import org.springframework.data.jpa.repository.*;

@Repository
public interface Banque1Repository extends JpaRepository<Compte,String>{
    
    
}