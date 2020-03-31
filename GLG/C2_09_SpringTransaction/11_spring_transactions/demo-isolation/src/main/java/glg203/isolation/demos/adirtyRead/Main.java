package glg203.isolation.demos.adirtyRead;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import glg203.isolation.dao.CompteRepository;
import glg203.isolation.model.Compte;
import static glg203.isolation.Utils.*;

/**
 * Démonstration de l'utilisation de SerializableRead
 */
@SpringBootApplication
@Configuration
@EntityScan(basePackages = "glg203.isolation.model")
@EnableJpaRepositories(basePackageClasses = { CompteRepository.class })
public class Main {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CompteRepository repository;

    @PostConstruct
    public void runMe() throws InterruptedException {
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
        // Pour éviter le dirty read, décommenter la ligne suivante :
        // transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        display("A réduit le compte de 100, puis échoue. B réduit le compte de 50");
        runCreerCompte();
        // On lance en parallèle deux traitements
        Thread thread1 = new Thread(() -> runTransactionA());
        Thread thread2 = new Thread(() -> runTransactionB());
        thread1.start();
        thread2.start();
        // On attend leur fin.
        thread1.join();
        thread2.join();
        runAfficherCompte();
        display("On attendrait un compte de 950, en READ_COMMITED");
    }

    private void runCreerCompte() {
        transactionTemplate.executeWithoutResult(e -> {
            Compte c = new Compte(1l, 1000);
            repository.save(c);
        });
    }

    private void runAfficherCompte() {
        transactionTemplate.executeWithoutResult(e -> {
            repository.findById(1l).ifPresent(c -> {
                display("Compte, solde " + c.getSolde());
            });
        });
    }

    // La transaction A Modifie le compte, puis s'annule.
    public void runTransactionA() {
        transactionTemplate.executeWithoutResult(e -> {
            Compte c = repository.findById(1l).get();
            int v = c.getSolde();
            c.setSolde(v - 100);
            repository.saveAndFlush(c); // Force l'update.
            display("Transaction A a mis le solde à " + c.getSolde());
            sleep(2000);
            display("Transaction A annulée");
            e.setRollbackOnly();
        });
    }

    public void runTransactionB() {
        transactionTemplate.executeWithoutResult(e -> {
            sleep(100); // On est sûr que A a fait sa modification (mais n'est pas encore annulée)    
            Compte c = repository.findById(1l).get();
            int v = c.getSolde();
            display("lecture pendant transaction B, solde " + v);  
            c.setSolde(v - 50);     
            repository.save(c);
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}