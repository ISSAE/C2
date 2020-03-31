package glg203.jpa03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * TransactionTemplate.
 * 
 */
public class TransactionTemplate {
    public static void performOperation(TransactionOperation op) {        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo1PU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        op.runIn(em);
        transaction.commit();        
        em.close();
        emf.close();
    }    
}