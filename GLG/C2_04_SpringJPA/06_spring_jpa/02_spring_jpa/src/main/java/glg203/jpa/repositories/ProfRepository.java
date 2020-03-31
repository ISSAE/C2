package glg203.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import glg203.jpa.model.Prof;

public interface ProfRepository extends CrudRepository<Prof,Long> {
    
}