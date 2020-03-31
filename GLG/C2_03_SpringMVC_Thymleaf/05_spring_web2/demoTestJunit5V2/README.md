# Démonstration de tests d'intégration pour une application web

On veut ici montrer l'utilisation de HtmlUnit 
et/ou WebDriver pour tester une application 
Web. Cette version utilise JUnit 5.


Ce qui est utilisé et démontré :

- MVC Spring avec Spring boot
- Vérification de formulaire en Javascript (de base!)
- Vérifications de formulaires avec les Contrôleurs de Spring MVC
- intégration de BootStrap et JQuery avec webjars
- internationalisation (limitée, mais réelle et testée!!)
- test d'intégration avec HtmlUnit.  

Le tout avec le minimum possible de configuration.


## Pour JUnit 4:

Modifier le `build.gradle` pour utiliser JUnit 4 et non 5 :


~~~~~gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation('org.springframework.boot:spring-boot-starter-test') 
	testImplementation 	'net.sourceforge.htmlunit:htmlunit'
}
~~~~~

Modifier le fichier de tests :

- Utiliser les *imports* de JUnit 4 et non 5 (et évidemment les classes et 
  méthodes associées) ;
- Ajouter l'annotation `@RunWith(SpringRunner.class)` pour lancer le test avec JUnit4. 

~~~~java
package glg203.demoTest;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class DemoTestIntegrationHtmlUnit {
	@Autowired
	private WebApplicationContext context;

	@Autowired
	WebClient webClient;

	
	@Test
	public void testAccueil() throws IOException {
		// Test simple, vérifie que la page d'accueil comprend
		// bien un h1 avec le texte "Formulaire de calcul".

		assertNotNull("Contexte est null", context);

		assertNotNull("Webclient est null", webClient);
		HtmlPage page = webClient.getPage("http://localhost:8080/");
		assertEquals(200, page.getWebResponse().getStatusCode());
		assertTrue("page html", page.isHtmlPage());
		DomNodeList<DomElement> titres = page.getElementsByTagName("h1");
		assertEquals(1, titres.size());
		DomElement titre = titres.get(0);
		assertEquals("Formulaire de calcul", titre.getTextContent());
	}
	
}
~~~~
