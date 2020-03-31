package glg203.demoTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class DemoTestIntegrationMockMvc {

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("Vérification de l'infrastructure de test")
	public void checkInjection() {
		assertNotNull(mockMvc, "MockMvc est null");
	}

	@Test
	@DisplayName("vérifie qu'une page demandée en anglais est bien en anglais")
	public void testEnglishLocale() throws Exception {
		// Note : pour utiliser xpath, le résultat doit être du XML bien formé !
		// Dans un premier temps, ce test a échoué parce que la balise <meta>
		// ne se terminait pas par "/>" !
		mockMvc.perform(get("http://localhost:8080/").locale(Locale.ENGLISH))
				.andExpect(xpath("//h1/text()").string("Computation Form"));
	}

	@Test
	@DisplayName("vérifie qu'une page demandée en français est bien en français")
	public void testFrenchLocale() throws Exception {
		mockMvc.perform(get("http://localhost:8080/").locale(Locale.FRENCH))
				.andExpect(xpath("//h1/text()").string("Formulaire de Calcul"));
	}

	@Test
	@DisplayName("vérifie l'accès à la page d'accueil")
	public void testAccueil() throws Exception {
		mockMvc.perform(get("http://localhost:8080/")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
	}

	@Test
	@DisplayName("Scenario standard: saisie de deux nombre, et affichage du résultat")
	public void testNominal() throws Exception {
		mockMvc.perform(post("/").param("a", "23").param("b", "76"))
			.andExpect(xpath("//*[@id='resultat']").string(Integer.toString(23+76)));		
	}

	// Pas de test possible de javascript...

	@Test
	@DisplayName("Test des messages d'erreur de validation")
	public void testMauvaiseValidation() throws Exception {
		mockMvc.perform(post("/").param("a", "23").param("b", "ds").locale(Locale.FRENCH))
		.andExpect(content().string(containsString("Entier attendu")));		
	}

}
