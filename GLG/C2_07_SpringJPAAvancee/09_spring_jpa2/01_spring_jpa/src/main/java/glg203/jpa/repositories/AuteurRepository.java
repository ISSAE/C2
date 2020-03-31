package glg203.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import glg203.jpa.model.heritage.Auteur;

/**
 * AuteurRepository
 */
public interface AuteurRepository extends JpaRepository<Auteur,Long>{

    
}