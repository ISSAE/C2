package net.cofares.ljug.repositories;

import net.cofares.ljug.model.Cours;
import org.springframework.data.repository.CrudRepository;


/**
 * CoursRepository
 */
public interface CoursRepository extends CrudRepository<Cours,Long>{

    public Cours findByLabel(String label);
    
}