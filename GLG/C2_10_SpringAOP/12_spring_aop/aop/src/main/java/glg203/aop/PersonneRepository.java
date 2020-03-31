package glg203.aop;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import glg203.aop.model.Personne;

/**
 * PersonneRepository
 * 
 * Note : le nom "standard" des méthodes est findBy... readBy est aussi reconnu.
 * Du coup, nous l'utilisons pour la partie DTO.
 * <p> Mais pour readAll, il faut définir la méthode.
 */
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    @Query("select p from Personne p")
    List<PersonneDTO> readAll();

	Optional<PersonneDTO> readById(Long id);
    
}