package glg203.jpa.repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import glg203.jpa.model.embed.Facture;

/**
 * FactureRepository
 */
public interface FactureRepository extends JpaRepository<Facture, Long> {

    // Force le chargement des détails de la facture.
    @EntityGraph(value = "facture.details",type = EntityGraphType.LOAD)
    //@EntityGraph(attributePaths = { "lignesCommandes"}, type = EntityGraphType.LOAD)   
    @Override
    List<Facture> findAll();

    /**
     * Même résultat que le findAll() précédent, mais en écrivant explicitement une
     * requête findAll.
     */
    @Query("select distinct f from Facture f left join fetch f.lignesCommandes ligne")
    List<Facture> findAllFetch();

}