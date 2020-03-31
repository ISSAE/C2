package glg203.securityDemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;


import glg203.securityDemo.dao.AuteurRepository;
import glg203.securityDemo.dao.MessageRepository;
import glg203.securityDemo.model.Auteur;
import glg203.securityDemo.model.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

// Pour Mock MVC
import org.springframework.test.web.servlet.MockMvc;
// Note : pour les import suivants, on utilise souvent un import statique.
// Pour bien voir qui vient d'où dans notre code, nous sommes 
// restés sur des imports "classiques".

// expédition des requêtes
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// accès au résultat
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
// méthode d'expédition des requêtes.
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

/**
 * Quelques tests...
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Pour éviter que les tests ne laissent des traces.
class SecurityDemoApplicationTests {

	@Autowired
	MessageRepository messagesRepository;

	@Autowired
	AuteurRepository auteurRepository;

	@Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	/**
	 * Teste que l'utilisateur anonyme a accès à certaines pages.
	 * (normalement, ce serait une bonne idée de faire un test par page).
	 */
	@Test
	@WithAnonymousUser
	public void testAnonymeAccessOk() throws Exception{
		// Page '/message'
		mockMvc.perform(MockMvcRequestBuilders.get("/message"))
			.andExpect(MockMvcResultMatchers.status().isOk());
		// page '/anonyme' (qu'il est le seul à voir)
		mockMvc.perform(MockMvcRequestBuilders.get("/anonyme"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser(roles = {"ADMIN", "USER"})
	public void testPageAnonymeInterditeSiAdmin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/anonyme"))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	/**
	 * Teste qu'un utilisateur connecté peut créer un message.
	 */
	@Test
	@WithMockUser(username = "toto")
	public void testCreationMessage() throws Exception {
		// Prélude : il faut que "toto" soit un auteur.
		auteurRepository.save(new Auteur("toto", "", "Toto l'utilisateur", false));		
		mockMvc.perform(MockMvcRequestBuilders.post("/message/creer")
			  .param("titre", "demo")
			  .param("texte", "le texte")
			  .with(SecurityMockMvcRequestPostProcessors.csrf())
			).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
		List<Message> l = messagesRepository.findByTitre("demo");
		assertEquals(1, l.size());
		assertEquals("le texte", l.get(0).getTexte());
		assertEquals("toto", l.get(0).getAuteur().getLogin());
	}

	/**
	 * Teste que l'utilisateur anonyme ne peut accéder à la page de création de message.
	 */
	@Test
	@WithAnonymousUser
	public void testAnonymeGetPageCreation() throws Exception{
		auteurRepository.save(new Auteur("toto", "", "Toto l'utilisateur", false));		
		mockMvc.perform(
			MockMvcRequestBuilders.get("/message/creer")
			  ).andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/login"));
		
	}

	/**
	 * Teste que l'utilisateur anonyme ne peut accéder à /zoneConnectee
	 */
	@Test
	@WithAnonymousUser
	public void testAnonymeGetPageConnectee() throws Exception{
		mockMvc.perform(
			MockMvcRequestBuilders.get("/zoneConnectee")
			  ).andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/login"));
		
	}

	/**
	 * Teste qu'un utilisateur connecté peut accéder à zoneConnectee'
	 */
	@Test
	@WithMockUser
	public void testGetPageConnectee() throws Exception{
		mockMvc.perform(
			MockMvcRequestBuilders.get("/zoneConnectee")
			  ).andExpect(MockMvcResultMatchers.status().isOk());		
	}
	/**
	 * Teste que l'utilisateur anonyme ne peut créer de message.
	 */
	@Test
	public void testAnonymeNonCreation() throws Exception{
		auteurRepository.save(new Auteur("toto", "", "Toto l'utilisateur", false));		
		mockMvc.perform(MockMvcRequestBuilders.post("/message/creer")
			  .param("titre", "demo")
			  .param("texte", "le texte")
			  .with(SecurityMockMvcRequestPostProcessors.csrf())
			).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
		List<Message> l = messagesRepository.findByTitre("demo");
		assertEquals(new ArrayList<Message>(), l);
	}

	/**
	 * Teste le scenario de connexion d'admin.	 
	 */
	@Test
	public void textConnexionAdmin() {
		
	}

	/**
	 * Teste que l'admin peut créer un utilisateur.
	 */
	@Test
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	public void testCreationUser() {

	}

	/**
	 * Teste qu'un utilisateur lambda ne peut créer d'utilisateur.
	 */

	/**
	 * Crée un utilisateur standard et logge celui-ci.
	 */
	@Test
	public void testConnexionUtilisateurLambda() {
		
	}



}
