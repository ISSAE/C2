package glg203.jpa03;

import glg203.jpa03.model.Cours;
import glg203.jpa03.model.Etudiant;

/**
 * DbInitHelper.
 * Initialise la base de donnée pour les expériences.
 */
public class DbInitHelper {

    public static void initDb() {
        TransactionTemplate.performOperation(em -> {
            // On initialise 5 cours... qu'on place dans une liste pour la suite...
            Cours cours[]= new Cours[5];
            for (int i= 0 ; i < cours.length; i++) {
                cours[i] = new Cours("cours"+i);
                em.persist(cours[i]);
            }
            // On initialise 100 étudiants            
            for (int i= 0 ; i < 100; i++) {
                Etudiant e= new Etudiant("etud"+i);
                for (Cours c: cours) {
                    c.addEtudiant(e);
                }
                em.persist(e);
            }
        });
    }

    
}