package glg203.carnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import glg203.carnet.modele.TypeEntree;

/**
 * TypeEntreeRepository
 */
@RestResource
public interface TypeEntreeRepository extends JpaRepository<TypeEntree, Long> {

    // Empêche la création et la modification de TypeEntree par REST.
    @Override
    @RestResource(exported = false)
    <S extends TypeEntree> S save(S entity);

}