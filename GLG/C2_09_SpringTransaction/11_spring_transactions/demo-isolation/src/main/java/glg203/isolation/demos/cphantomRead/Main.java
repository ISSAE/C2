package glg203.isolation.demos.cphantomRead;

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

/**
 * Démonstration de lecture fantôme en ISOLATION_REPEATABLE_READ.
 * 
 * La transaction A lit deux fois le nombre de comptes.
 * La transaction B, entre-temps, ajoute un compte.
 * 
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
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        // à utiliser pour éviter les phantom reads.
        // transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
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
        display("Sans Phantom Read, A devrait voir les deux fois le même nombre de comptes.");
    }

    private void runCreerCompte() {
        transactionTemplate.executeWithoutResult(e -> {
            Compte c = new Compte(1l, 1000);
            repository.save(c);
        });
    }

    private void runAfficherCompte() {
        transactionTemplate.executeWithoutResult(e -> {
            display("Il y a au final " + repository.count() + " comptes");
        });
    }

    public void runTransactionA() {
        transactionTemplate.executeWithoutResult(e -> {                       
            long v = repository.count();
            display("Transaction A a vu "+v + " comptes");
            sleep(5000);
            v = repository.count();           
            display("Transaction A a maintenant vu " + v + " comptes"); 
        });
    }

    public void runTransactionB() {
        transactionTemplate.executeWithoutResult(e -> {
            sleep(100);
            Compte c = new Compte(2l, 1000);
            repository.save(c);
            display("Transaction B crée un compte de plus.");
        });
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {          
            // NE RIEN FAIRE.
        }
    }

    public void display(String toDisplay) {
        String line = "***************************************************************";
        System.out.println(line);
        System.out.println("*");
        System.out.println("*  "+ toDisplay );
        System.out.println("*");
        System.out.println(line);                
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
}