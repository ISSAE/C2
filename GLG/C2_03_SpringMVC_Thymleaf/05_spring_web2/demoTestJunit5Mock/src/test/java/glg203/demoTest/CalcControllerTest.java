package glg203.demoTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import glg203.demoTest.service.CalcService;
import glg203.demoTest.ui.CalcController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.mockito.Mockito.when;

/**
 * Tests unitaires de CalcController.
 * 
 * Dans ce test, le contrôleur est testé indépendamment de la vue, mais aussi du
 * service.
 * 
 * Pour le vérifier : modifier CalcServiceImpl pour qu'il renvoie un résultat
 * faux. Les tests de la présente classe devraient continuer à fonctionner.
 */

@WebMvcTest(CalcController.class)
public class CalcControllerTest {

	@Autowired
	CalcController calcController;

	// Prépare un bean mock.
	@MockBean
	CalcService calcService;

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("Scenario standard: saisie de deux nombre, et affichage du résultat")
	public void testNominal() throws Exception {
		when(calcService.somme(5, 7)).thenReturn(12);
		mockMvc.perform(post("/").param("a", "5").param("b", "7")).andExpect(view().name("calcForm"))
				.andExpect(model().attribute("resultat", 12));
	}

	@Test
	@DisplayName("Test des messages d'erreur de validation")
	public void testMauvaiseValidation() throws Exception {
		mockMvc.perform(post("/").param("a", "23").param("b", "ds"))
			.andExpect(status().isOk())			
			.andExpect(model().attributeHasFieldErrors("calcForm", "b"))
			.andExpect(view().name("calcForm"));
	}

	@Configuration
	@ComponentScan(basePackageClasses = CalcController.class)	
	static class Config {
	}

}