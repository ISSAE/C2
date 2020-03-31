package glg203.isolation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import glg203.isolation.model.TimeTable;

/**
 * TimeTableRepository
 */
public interface TimeTableRepository extends JpaRepository<TimeTable,Long> {

    
}