package net.cofares.ljug.repositories;

import java.util.Set;
import net.cofares.ljug.model.Cours;
import net.cofares.ljug.model.Etudiant;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



/**
 * EtudiantRepository
 */
public interface EtudiantRepository extends CrudRepository<Etudiant,Long> {
    public Iterable<Etudiant> findByAddresse(String addresse);

    public Iterable<Etudiant> findByNomOrPrenom(String nom, String prenom);

    @Query("select e from Etudiant e where :cours member of e.cours")
    public Set<Etudiant> findByCours(@Param("cours") Cours cours); 
}