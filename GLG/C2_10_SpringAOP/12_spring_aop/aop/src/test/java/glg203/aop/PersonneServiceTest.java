package glg203.aop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;

import glg203.aop.aspect.AOPLog;

@SpringBootTest
@EnableAspectJAutoProxy
@Transactional
class PersonneServiceTest {

	@Autowired 
	PersonneService service;

	@Autowired AOPLog aopLog;

	@Autowired
	UserConnexion userConnexion;

	@Test
	@SuppressWarnings("unused")
	void demoRename() {
		userConnexion.login("user");
		Long id = service.creerPersonne("toto");
		service.renamePersonne(id, "titi");
		PersonneDTO pDto = service.readById(id);
		userConnexion.logout();
		// Trace de creerPersonne : 
		assertEquals("On va appeler la méthode de service creerPersonne", aopLog.popFirstMessage());
		assertEquals("On va appeler la méthode de repository save", aopLog.popFirstMessage());
		assertEquals("On va sauver toto", aopLog.popFirstMessage());
		// 
		assertEquals("On va appeler la méthode de service renamePersonne", aopLog.popFirstMessage());
		assertEquals("On va appeler la méthode de repository findById", aopLog.popFirstMessage());
		assertEquals("On va appeler la méthode de service readById", aopLog.popFirstMessage());
		assertEquals("on appelle readById 1 dans glg203.aop.PersonneService", aopLog.popFirstMessage());
		assertEquals("On va appeler la méthode de repository readById", aopLog.popFirstMessage());
		assertEquals("on appelle readById 1 dans glg203.aop.PersonneRepository", aopLog.popFirstMessage());
	}

	@Test
	void demoSecurite() {
		Assertions.assertThrows(UtilisateurNonConnecteException.class,() -> {
			userConnexion.logout(); // des fois que...
			service.creerPersonne("toto");		
		});
	}

	@Test
	void demoSecuriteOk() {
		// On vérifie que les méthodes de lecture ne lèvent pas d'exception si on n'est pas connecté.
		userConnexion.logout();
		service.readById(1l);
		service.readPersonnes();
	}
}
