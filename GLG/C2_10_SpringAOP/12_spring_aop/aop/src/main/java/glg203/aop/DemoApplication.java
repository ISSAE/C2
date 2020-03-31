package glg203.aop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import glg203.aop.aspect.AOPLog;

@SpringBootApplication
public class DemoApplication {

	@Autowired 
	PersonneService service;

	@Autowired AOPLog aopLog;

	@Autowired
	UserConnexion userConnexion;
	
	@PostConstruct
	public void runMe() {
		System.out.println("********************************************");
		System.out.println("Le code intéressant n'est pas l'application");
		System.out.println("regardez aussi les tests !!!");
		System.out.println("********************************************");
		userConnexion.login("user");
		Long id = service.creerPersonne("toto");
		service.renamePersonne(id, "titi");
		PersonneDTO pDto = service.readById(id);
		userConnexion.logout();
		// On affiche le log...
		String message;
		while ((message = aopLog.popFirstMessage())!= null) {
			System.out.println(message);
		}
		// On déclenche une exception ?
		// userConnexion.login("user");
		// service.plante();

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}

}
