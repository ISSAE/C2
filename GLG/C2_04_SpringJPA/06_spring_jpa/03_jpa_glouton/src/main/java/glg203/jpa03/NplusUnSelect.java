package glg203.jpa03;

import glg203.jpa03.model.Cours;
import glg203.jpa03.model.Etudiant;

/**
 * NplusUnSelect.
 * 
 * Application démontrant un problème de n+1 select.
 * 
 * <p>à tester en affichant le SQL exécuté.
 */
public class NplusUnSelect {
    public static void run() {
       DbInitHelper.initDb();
       // Opération qui va déclencher N + 1 Select :
        TransactionTemplate.performOperation(em -> {
            for (Cours c : 
                em.createQuery("select c from Cours c", Cours.class).getResultList()) {
                for (Etudiant e : c.getEtudiants()) {
                    String n = e.getNom();
                    System.out.println(n);
                }
            }
        });

    }
    public static void main(String[] args) {
         run();
    }
}