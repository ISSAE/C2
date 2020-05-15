package lb.edu.isae.voituredb.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VoitureRepository extends CrudRepository <Voiture, Long> {
	// Fetch cars by brand and sort by year
	List<Voiture> findByBrandOrderByYearAsc(String brand);
}
