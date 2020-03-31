package glg203.jpa2.criteria;


import javax.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Démonstration d'utilisation de JPQL et de l'api criteria.
 */
@SpringBootApplication
public class CriteriaApplication {


	private void rechercheSimple() {
		

	}

	@PostConstruct
	public void runAll() {
		rechercheSimple();
	}

	public static void main(String[] args) {
		System.out.println("Tout le code pertinent est dans les tests !");
	}

}
