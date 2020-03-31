package glg203.isolation.demos.dSerializable;

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
 * Cet exemple montre les limites de l'isolation Serializable.
 * 
 * <ul>
 * <li> Avec H2, elle ne fonctionne pas bien : les modifications ont lieu en parallèle, et c'est la dernière qui 
 * a parlé qui gagne. Noter qu'à l'intérieur d'une transaction, tout se passe de manière logique ;
 * <li> Avec Derby, on a un problème de lock et un plantage. la documentation de 
 *      certaines bases explicite que c'est le comportement attendu dans certains cas. 
 *      Il faut alors relancer la transaction qui a échoué.
 * </ul>
 * 
 * Pour le cas qui nous occupe, un verrou optimiste aurait été plus indiqué.
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
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
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
        display("Le résultat correct serait un solde de 1000 - 100 - 10 soit 890.");
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
            Compte c = repository.findById(1l).get();
            int v = c.getSolde();
            display("Transaction A a vu un solde de "+v);
            sleep(1000);
            c.setSolde(c.getSolde() - 100);
            display("Transaction A réduit le solde de 100 et le fixe à  " + c.getSolde());
        });
    }

    public void runTransactionB() {
        transactionTemplate.executeWithoutResult(e -> {
            sleep(100);
            Compte c = repository.findById(1l).get();
            int v = c.getSolde();        
            display("Transaction B voit le solde de "+c.getSolde());  
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