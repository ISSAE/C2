package glg203.jpa.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import glg203.jpa.model.Cours;
import glg203.jpa.model.Etudiant;

/**
 * Quelques essais simples sur la classe "Etudiant" et son repository.
 * Permettent en plus de montrer le comportement de SpringBoot lors des tests.
 */
@SpringBootTest
@Transactional
public class EtudiantRepositoryTest {

    @Autowired
    EtudiantRepository repository;

    @Autowired
    CoursRepository coursRepository;

    private void creerEtudiants() {
        Cours[] cours= new Cours[3];
        for (int i= 1; i<= 3; i++) {
            cours[i-1] = new Cours("c"+i);
            coursRepository.save(cours[i-1]);
        }

        // Bon, on s'amuse avec les lambda :
        LongStream.iterate(0l, (x) -> x + 1l).limit(100).forEach(
            i -> {
                Etudiant p = new Etudiant("n"+i,"p"+i,"a"+ (i%10));
                p.ajouterCours(cours[0]);
                if (i % 20 == 0) {
                    p.ajouterCours(cours[1]);
                }
                repository.save(p);
            }
        );;
    }
    @Test
    public void testCreation() {
        Etudiant p= new Etudiant("n1", "p1", "a1");
        p = repository.save(p);
        assertNotNull(p.getId());
    }


    @Test
    public void testFindById() {
        try {
            Etudiant p= new Etudiant("n1", "p1", "a1");
            p = repository.save(p);
            Etudiant p1 = repository.findById(p.getId()).get();
            assertEquals(p1.getId(), p1.getId());
        } catch (NoSuchElementException e) {
            fail("non trouvé");
        }
    }

    @Test
    public void testFindByAddress() {
        creerEtudiants();
        // Une personne sur les 100 a l'adresse a2...
        Iterable<Etudiant> iter = repository.findByAddresse("a2");
        long nb = StreamSupport.stream(iter.spliterator(), false).count();
        assertEquals(10l, nb);
    }

    @Test
    public void testFindAll() {
        creerEtudiants();
        assertEquals(100, repository.count());
    }
    /**
     * Nous avons défini le jeu de tests comme "transactionnel".
     * Du coup, Spring annule toutes les modifications 
     * à chaque test. 
     * C'est pratique, car on peut écrire les tests indépendamment les uns des autres.
     * 
     * Ce test fonctionne en combinaison avec demoRollbackTestB.
     * Comme chacun teste que la base est vide, puis ajoute une entrée, cela montre
     * que la base est vidée après le test.
     * 
     * Attention, ce comportement n'est obtenu que si la classe de test est annotée avec @{@link Transactional}
     */
    @Test
    public void demoRollbackTestA() {
        assertEquals(0, repository.count());
        Etudiant p= new Etudiant("n1", "p1", "a1");
        p = repository.save(p);        
        assertEquals(1, repository.count());
    }

    /**
     * @see #demoRollbackTestA()
     */
    @Test
    public void demoRollbackTestB() {
        assertEquals(0, repository.count());
        Etudiant p= new Etudiant("n1", "p1", "a1");
        p = repository.save(p);        
        assertEquals(1, repository.count());
    }

    @Test
    public void demoSearchOr() {
        creerEtudiants();
        long n = StreamSupport.stream(repository.findByNomOrPrenom("n1", "p2").spliterator(), false).count();
        assertEquals(2l, n);
        n = StreamSupport.stream(repository.findByNomOrPrenom("n1", "n1").spliterator(), false).count();
        assertEquals(1l, n);
    }

    @Test
    public void demoQueryA() {
        creerEtudiants();
        Cours c2= coursRepository.findByLabel("c2");
        Set<Etudiant> s= repository.findByCours(c2);
        assertTrue(s.size() > 0);
        assertTrue(s.size() < 100);
    }

    @Test
    public void demoQueryB() {
        creerEtudiants();
        Cours c1= coursRepository.findByLabel("c1");
        Set<Etudiant> s= repository.findByCours(c1);
        assertTrue(s.size() == 100);
    }

    @Test
    public void demoQueryC() {
        creerEtudiants();
        Cours c3= coursRepository.findByLabel("c3");
        Set<Etudiant> s= repository.findByCours(c3);
        assertTrue(s.size() == 0);
    }

}