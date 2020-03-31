package glg203.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import glg203.jpa.model.embed.TableDesMatieres;

/**
 * TableDesMatieresRepository
 */
public interface TableDesMatieresRepository extends JpaRepository<TableDesMatieres, Long> {
    @EntityGraph(attributePaths = { "entrees" }, type = EntityGraphType.LOAD)
    @Override
    List<TableDesMatieres> findAll();
}