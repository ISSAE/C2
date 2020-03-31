package glg203.jpa03;

import javax.persistence.EntityManager;

/**
 * TransactionOperation
 */
@FunctionalInterface
public interface TransactionOperation {

    void runIn(EntityManager em);
    
}