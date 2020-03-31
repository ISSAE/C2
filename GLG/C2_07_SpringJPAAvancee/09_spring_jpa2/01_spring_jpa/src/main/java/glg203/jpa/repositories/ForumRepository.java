package glg203.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import glg203.jpa.model.cascade.Forum;

/**
 * ForumRepository
 */
public interface ForumRepository extends JpaRepository<Forum,Long>{

    @Override
    @EntityGraph(attributePaths = {"messages"})
    List<Forum> findAll();
}