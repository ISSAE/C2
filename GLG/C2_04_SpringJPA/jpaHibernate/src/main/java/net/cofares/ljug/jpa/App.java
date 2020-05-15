package net.cofares.ljug.jpa;

import java.io.Console;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import net.cofares.ljug.jpa.model.Prof;

public class App {


    Console console = System.console();

    public void run() {
        creerProfs();
        listerProfs();
        mettreAJourProf();
        montrerProfModifie();
    }

    private void listerProfs() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo1PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Prof> l = em.createQuery("select p from Prof p", Prof.class).getResultList();
        for (Prof p: l) {
            System.out.println(p.getNom());
        }
        transaction.commit();        
        em.close();
        emf.close();
    }

    /**
     * Premier exemple: création et sauvegarde d'un objet.
     */
    private void creerProfs() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo1PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        for (int i = 0; i < 100; i++) {
            Prof prof = new Prof("Lovelace" + i, "Ada" + i);
            em.persist(prof);
        }
        transaction.commit();        
        em.close();
        emf.close();
    }

    /**
     * Démontre la mise à jour d'une entrée "prof"
     */
    private void mettreAJourProf() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo1PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        // Changeons le Nom de famille du prof dont le prénom est "Ada10"
        Prof p = em.createQuery("select p from Prof p where p.prenom = :prenom", Prof.class)
            .setParameter("prenom", "Ada10")
            .getResultList()
            .get(0);
        p.setNom("Byron");
        transaction.commit();        
        em.close();
        emf.close();                        
    }

    /**
     * Montre le prof dont on a modifié le nom de famille...
     */
    private void montrerProfModifie() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo1PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        // Changeons le Nom de famille du prof dont le prénom est "Ada10"
        Prof p = em.createQuery("select p from Prof p where p.prenom = :prenom", Prof.class)
            .setParameter("prenom", "Ada10")
            .getResultList()
            .get(0);
        System.out.println(p);
        transaction.commit();        
        em.close();
        emf.close();    
    }
    public static void main(String[] args) {
        new App().run();
    }
}
