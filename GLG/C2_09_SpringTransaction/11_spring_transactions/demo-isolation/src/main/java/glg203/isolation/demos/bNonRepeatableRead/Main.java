package glg203.isolation.demos.bNonRepeatableRead;

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
 * Démonstration de lecture non répétables en READ_COMMITED.
 * 
 * La transaction A lit deux fois le solde (et l'affiche en conséquence).
 * On pourrait par exemple supposer que la première lecture corresponde à un test.
 * La transaction B, entre-temps, modifie la valeur du solde.
 * 
 * On utilise la méthode getSolde() du repository pour éviter d'éventuelles 
 * intéractions avec les entités JPA, qui gardent en mémoire la valeur qu'elles ont lue.
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
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        // à utiliser pour éviter les non repeatable reads :
        // transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
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
        display("Avec Repeatable Read, A devrait voir les deux fois la même valeur, et la valeur finale devrait être 990");
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

    public void runTransactionA() {
        transactionTemplate.executeWithoutResult(e -> {                       
            int v = repository.getSolde(1l);
            display("Transaction A a vu un solde de " + v);
            sleep(5000);
            v = repository.getSolde(1l);           
            display("Transaction A a maintenant vu un solde de " + v);            
        });
    }

    public void runTransactionB() {
        transactionTemplate.executeWithoutResult(e -> {
            sleep(100);
            Compte c = repository.findById(1l).get();
            int v = c.getSolde();                      
            c.setSolde(v - 10);
            display("Transaction B fixe le solde à "+c.getSolde());
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