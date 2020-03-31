package glg203.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import glg203.jpa.model.Cours;

/**
 * CoursRepository
 */
public interface CoursRepository extends CrudRepository<Cours,Long>{

    public Cours findByLabel(String label);
    
}