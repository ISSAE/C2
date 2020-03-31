package glg203.jpa01.dao;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * PersonneRepository
 */

import glg203.jpa01.model.Personne;
public interface PersonneRepository extends JpaRepository<Personne,Long> {

}