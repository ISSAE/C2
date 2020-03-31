package glg203.demoTest;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.swing.text.html.HTML;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class DemoTestIntegrationHtmlUnit {

	@Autowired
	WebClient webClient;


	@Test
	@DisplayName("Vérification de l'infrastructure de test")
	public void checkInjection() {
		assertNotNull(webClient, "Webclient est null");
	}

	@Test
	@DisplayName("vérifie qu'une page demandée en anglais est bien en anglais")
	public void testEnglishLocale() throws IOException {
		webClient.addRequestHeader("Accept-Language", "en");
		HtmlPage page = webClient.getPage("http://localhost:8080/");
		DomNode h1Node = page.getFirstByXPath("//h1");
		assertEquals("Computation Form", h1Node.getTextContent());
		webClient.removeRequestHeader("Accept-Language");
	}

	@Test
	@DisplayName("vérifie qu'une page demandée en français est bien en français")
	public void testFrenchLocale() throws IOException {
		webClient.addRequestHeader("Accept-Language", "fr");
		HtmlPage page = webClient.getPage("http://localhost:8080/");
		DomNode h1Node = page.getFirstByXPath("//h1");
		assertEquals("Formulaire de Calcul", h1Node.getTextContent());
		webClient.removeRequestHeader("Accept-Language");
	}


	@Test
	@DisplayName("vérifie l'accès à la page d'accueil")
	public void testAccueil() throws IOException {
		HtmlPage page = webClient.getPage("http://localhost:8080/");
		assertAll(
				() ->
						assertEquals(200, page.getWebResponse().getStatusCode()),
				() ->
						assertTrue(page.isHtmlPage(), "page html")
		);
	}


	@Test
	@DisplayName("Scenario standard: saisie de deux nombre, et affichage du résultat")
	public void testNominal() throws IOException {
		HtmlPage page2 = essaiFormulaire("23", "76");
		assertEquals(Integer.toString(23 + 76), page2.getElementById("resultat").getTextContent());
	}

	@Test
	@DisplayName("Test de la validation d'erreur en JS")
	public void testCheck() throws IOException {
		// On vérifie seulement que les champs ne sont pas vides.
		HtmlPage page2 = essaiFormulaire("", "21");
		assertEquals("les champs ne doivent pas être vide", page2.getElementById("erreur").getTextContent());
	}

	@Test
	@DisplayName("Test des messages d'erreur de validation")
	public void testMauvaiseValidation() throws IOException {
		// On vérifie que les champs sont bien des entiers.
		HtmlPage page2 = essaiFormulaire("10", "ds");

	}


	/**
	 * Méthode auxiliaire: lance le formulaire avec deux valeurs passées en paramètres.
	 * @param valA
	 * @param valB
	 * @return
	 * @throws IOException
	 */
	private HtmlPage essaiFormulaire(String valA, String valB) throws IOException {
		HtmlPage form = webClient.getPage("http://localhost:8080/");
		HtmlTextInput inputA = (HtmlTextInput) form.getElementById("a");
		HtmlTextInput inputB = (HtmlTextInput) form.getElementById("b");
		inputA.type(valA);
		inputB.type(valB);
		HtmlSubmitInput button = (HtmlSubmitInput) form.getElementById("submit");
		HtmlPage page2 = button.click();
		return page2;
	}


}
