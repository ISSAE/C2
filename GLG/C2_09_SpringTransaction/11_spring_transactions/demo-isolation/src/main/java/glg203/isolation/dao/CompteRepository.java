package glg203.isolation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import glg203.isolation.model.Compte;

/**
 * CompteRepository
 */
public interface CompteRepository extends JpaRepository<Compte,Long> {

    @Query("select c.solde from Compte c where id = :id")
    int getSolde(@Param("id") long id);
}