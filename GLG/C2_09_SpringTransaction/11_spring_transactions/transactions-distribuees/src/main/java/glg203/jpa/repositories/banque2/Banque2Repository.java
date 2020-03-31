package glg203.jpa.repositories.banque2;

// VOIR https://dzone.com/articles/multiple-databases-with-shared-entity-classes-in-s
import org.springframework.stereotype.Repository;

import glg203.jpa.model.Compte;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Repository
@EnableJpaRepositories(basePackageClasses = Banque2Repository.class)
public interface Banque2Repository extends JpaRepository<Compte,String>{
    
    
}