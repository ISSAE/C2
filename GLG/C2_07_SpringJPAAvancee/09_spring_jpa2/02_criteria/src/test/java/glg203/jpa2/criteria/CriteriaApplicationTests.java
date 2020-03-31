package glg203.jpa2.criteria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import glg203.jpa2.criteria.model.*;

@SpringBootTest
@Transactional
class CriteriaApplicationTests {

	@PersistenceContext
	private EntityManager em;

	private Adresse creerAdresse() {
		Random random = new Random();
		return new Adresse("rue " + random.nextInt(100), "ville " + random.nextInt(4));
	}

	/**
	 * Cette méthode initialise la base. Le système utilisé ne correspond pas aux
	 * cas usuels...
	 * <p>
	 * Généralement, on a une base bien définie, et on utilise des moyens autres que
	 * JPA pour la remplir.
	 */
	@BeforeEach
	public void prepareBase() {
		Random random = new Random();
		ArrayList<Universite> universites = new ArrayList<>();
		// Les universités.
		for (int i = 0; i < 3; i++) {
			Universite universite = new Universite("univ " + i, creerAdresse());
			universites.add(universite);
			em.persist(universite);
			// leurs formations
			Formation licence = new Formation("Licence", universite);
			em.persist(licence);
			Formation master = new Formation("Master", universite);
			em.persist(master);
			// leurs professeurs
			ArrayList<Professeur> professeurs = new ArrayList<>();
			for (int j = 0; j < random.nextInt(10) + 5; j++) {
				Professeur p = new Professeur("prof " + random.nextInt(), "prenom", creerAdresse(), universite);
				em.persist(p);
				professeurs.add(p);
			}
			// Les cours...
			Cours c1 = new Cours("nfa031", licence, professeurs.get(random.nextInt(professeurs.size())));
			Cours c2 = new Cours("nfa008", licence, professeurs.get(random.nextInt(professeurs.size())));
			Cours c3 = new Cours("nfp121", licence, professeurs.get(random.nextInt(professeurs.size())));
			em.persist(c1);
			em.persist(c2);
			em.persist(c3);
			// Les Universités 0 et 1 ont un cours nfa0016
			if (i == 0 || i == 1) {
				Cours nfa0016 = new Cours("nfa016", licence, professeurs.get(random.nextInt(professeurs.size())));
				nfa0016.addTheme(new Theme("web"));
				em.persist(nfa0016);
			}
			// Les Universités 1 et 2 ont un cours nfa0017
			if (i == 1 || i == 2) {
				Cours nfa0017 = new Cours("nfa017", licence, professeurs.get(random.nextInt(professeurs.size())));
				nfa0017.addTheme(new Theme("web"));
				em.persist(nfa0017);
			}
			// Les thèmes
			Theme programmation = new Theme("programmation");
			Theme java = new Theme("java");
			Theme algorithmique = new Theme("algorithmique");
			Theme basesDeDonnees = new Theme("bases de données");
			Theme patterns = new Theme("patterns");
			Theme analyse = new Theme("analyse");
			c1.addTheme(programmation);
			c1.addTheme(algorithmique);
			c1.addTheme(java);
			c2.addTheme(basesDeDonnees);
			c2.addTheme(analyse);
			c3.addTheme(programmation);
			c3.addTheme(analyse);
			c3.addTheme(patterns);
			c3.addTheme(java);
			// Les étudiants de Licence.
			for (int j = 0; j < 30 + random.nextInt(70); j++) {
				Etudiant e = new Etudiant("n" + random.nextInt(), "p" + random.nextInt(), creerAdresse(), licence);
				em.persist(e);
			}
			// Les étudiants de Master.
			for (int j = 0; j < 30 + random.nextInt(70); j++) {
				Etudiant e = new Etudiant("n" + random.nextInt(), "p" + random.nextInt(), creerAdresse(), master);
				em.persist(e);
			}
		}
	}

	@Test
	public void checkNotNull() {
		assertNotNull(em);
	}

	/**
	 * Teste que les thèmes sont bien liés aux cours. (rien à voir avec Criteria)
	 */
	@Test
	public void testThemes() {
		String jpql = "select c from Cours c where c.titre = 'nfa031'";
		List<Cours> l = em.createQuery(jpql, Cours.class).getResultList();
		assertTrue(l.size() > 0, "Cours non trouvés");
		assertTrue(l.get(0).getThemes().contains(new Theme("programmation")), "thèmes non sauvés");
	}

	/**
	 * Compare les résultats d'un select simple en JPQL et en Criteria.
	 */
	@Test
	void compareSelectSimple() {
		// Requête JPQL
		String jpql = "select e from Etudiant e";
		List<Etudiant> l1 = em.createQuery(jpql, Etudiant.class).getResultList();
		assertTrue(l1.size() > 0, "Liste vide ???");

		// La même en criteria :
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Etudiant> query = builder.createQuery(Etudiant.class);
		query.select(query.from(Etudiant.class));
		TypedQuery<Etudiant> typedQuery = em.createQuery(query);
		List<Etudiant> l2 = typedQuery.getResultList();
		// On compare les résultats.
		assertEquals(new HashSet<>(l1), new HashSet<>(l2));
	}

	@Test
	void compareSelectAvecClause() {
		// Requête JPQL
		String jpql = "select c from Cours c where c.titre = :titre";
		List<Cours> l1 = em.createQuery(jpql, Cours.class).setParameter("titre", "nfa031").getResultList();
		assertTrue(l1.size() > 0, "Liste vide ???");

		// La même en criteria :
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cours> query = builder.createQuery(Cours.class);
		Root<Cours> root = query.from(Cours.class);
		query.select(root);
		ParameterExpression<String> titre = builder.parameter(String.class);
		query.where(builder.equal(root.get("titre"), titre));
		TypedQuery<Cours> typedQuery = em.createQuery(query);
		typedQuery.setParameter(titre,"nfa031");
		List<Cours> l2 = typedQuery.getResultList();
		// On compare les résultats.
		assertEquals(new HashSet<>(l1), new HashSet<>(l2));
	}

	/**
	 * Recherche les universités qui proposent à la fois des cours de NFA016 et de
	 * NFA017.
	 */
	@Test
	void compareAvecJointure() {
		// Requête JPQL
		Universite univ1 = em.createQuery("select u from Universite u where u.nom = 'univ 1'", Universite.class)
				.getSingleResult();
		String jpql = "select distinct c1.formation.universite from Cours c1, Cours c2 "
				+ "where c1.titre = 'nfa016' and c2.titre = 'nfa017' "
				+ "and c1.formation.universite = c2.formation.universite";
		List<Universite> universites = em.createQuery(jpql, Universite.class).getResultList();
		// On teste que la requête JPQL a fonctionné
		assertEquals(Arrays.asList(univ1), universites);

		// Requête Criteria
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Universite> query = cb.createQuery(Universite.class).distinct(true);
		Root<Cours> cours1 = query.from(Cours.class);
		Root<Cours> cours2 = query.from(Cours.class);
		query.where(cb.and(cb.equal(cours1.get("titre"), "nfa016"), cb.equal(cours2.get("titre"), "nfa017"),
				cb.equal(cours1.get("formation").get("universite"), cours2.get("formation").get("universite"))));
		query.select(cours1.get("formation").get("universite"));
		TypedQuery<Universite> typedQuery = em.createQuery(query);
		List<Universite> result1 = typedQuery.getResultList();
		assertEquals(Arrays.asList(univ1), result1);
	}

	/**
	 * On a fait générer des classes "métamodèle" à partir des entités (voir le build.gradle).
	 * Ces classes sont utilisables pour obtenir un code type-safe.
	 */
	@Test
	void compareAvecJointureTypeSafe() {		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Universite> query = cb.createQuery(Universite.class).distinct(true);
		Root<Cours> cours1 = query.from(Cours.class);
		Root<Cours> cours2 = query.from(Cours.class);
		// Nous utilisons ici les 
		query.where(cb.and(
				cb.equal(cours1.get(Cours_.titre), "nfa016"),
				cb.equal(cours2.get(Cours_.titre), "nfa017"),
				cb.equal(
					cours1.get(Cours_.formation).get(Formation_.universite), 
					cours2.get(Cours_.formation).get(Formation_.universite)
				)));
		query.select(cours1.get(Cours_.formation).get(Formation_.universite));
		TypedQuery<Universite> typedQuery = em.createQuery(query);
		List<Universite> result1 = typedQuery.getResultList();
		assertEquals(1, result1.size());
		assertEquals("univ 1", result1.get(0).getNom());
	}

	@Test
	public void compareAvecJointure2() {
		// Teste une requête sur deux thèmes.
		String jpql = "select c from Cours c join c.themes t1 join c.themes t2 "
				+ "where t1.label = :l1 and t2.label = :l2";
		List<Cours> cours = em.createQuery(jpql, Cours.class).setParameter("l1", "algorithmique")
				.setParameter("l2", "programmation").getResultList();
		assertTrue(cours.size() > 0);
		cours.forEach(c -> assertEquals("nfa031", c.getTitre()));

		// Requête Criteria
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cours> query = cb.createQuery(Cours.class);
		Root<Cours> root = query.from(Cours.class);
		Join<Object, Object> theme1 = root.join("themes");
		Join<Object, Object> theme2 = root.join("themes");		
		ParameterExpression<String> l1 = cb.parameter(String.class);
		ParameterExpression<String> l2 = cb.parameter(String.class);
		query.where(
			cb.and(
				cb.equal(theme1.get("label"), l1),
				cb.equal(theme2.get("label"), l2)				
			));
		TypedQuery<Cours> typedQuery = em.createQuery(query);
		typedQuery.setParameter(l1, "algorithmique");
		typedQuery.setParameter(l2, "programmation");
		List<Cours> cours01 = typedQuery.getResultList();
		assertTrue(cours01.size() > 0);
		cours01.forEach(c -> assertEquals("nfa031", c.getTitre()));
	}

	@Test
	public void chercheAvecMemberOf() {
		// Version JPQL
		String jpql = "select c from Cours c where :theme member of c.themes";
		List<Cours> cours = em.createQuery(jpql, Cours.class).setParameter("theme", new Theme("algorithmique"))
				.getResultList();
		assertTrue(cours.size() > 0);
		cours.forEach(c -> assertEquals("nfa031", c.getTitre()));

		// Version Criteria
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cours> query = cb.createQuery(Cours.class);
		Root<Cours> root = query.from(Cours.class);
		query.where(cb.isMember(new Theme("algorithmique"), root.get("themes")));
		TypedQuery<Cours> typedQuery = em.createQuery(query);
		List<Cours> cours01 = typedQuery.getResultList();
		assertTrue(cours01.size() > 0);
		cours01.forEach(c -> assertEquals("nfa031", c.getTitre()));
	}

	/**
	 * Test pour chercher les cours qui ont <em>tous</em> les thèmes définis dans
	 * une collection.
	 * <p>
	 * Avec JPQL, il faut écrire une condition par thème. Le texte de la requête est
	 * donc variable. C'est justement le <em>use case</em> favorable à Criteria.
	 */
	@Test
	public void chercheAvecCollection() {
		List<Theme> themes = Arrays.asList(new Theme("algorithmique"), new Theme("programmation"));
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cours> query = cb.createQuery(Cours.class);
		Root<Cours> root = query.from(Cours.class);
		List<Predicate> conditions = new ArrayList<>();
		for (Theme theme : themes) {
			Predicate themeIn = cb.isMember(theme, root.get("themes"));
			conditions.add(themeIn);
		}
		query.where(conditions.toArray(new Predicate[conditions.size()]));
		TypedQuery<Cours> typedQuery = em.createQuery(query);
		List<Cours> cours = typedQuery.getResultList();
		assertTrue(cours.size() > 0);
		cours.forEach(c -> assertEquals("nfa031", c.getTitre()));
	}

}
