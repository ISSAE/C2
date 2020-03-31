package glg203.isolation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import glg203.isolation.dao.CompteRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Démonstration de lock pessimistes.
 * 
 * Scénario :
 * <ul>
 * trois opérations de consultations sont lancées (et durent 5 secondes) puis
 * une écriture
 */
@SpringBootApplication
@Configuration
@EntityScan(basePackages = "glg203.isolation.model")
@EnableJpaRepositories(basePackageClasses = { CompteRepository.class })
public class Main {

    @Autowired
    MesServices service;

    @Autowired
    Utils utils;

    @PostConstruct
    public void runMe() throws InterruptedException {
        service.creerComptes();
        utils.sleep(100);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> service.lecture(1, 1, 20));
        executorService.submit(() -> service.lecture(2, 2, 1));
        executorService.submit(() -> service.ecriture(3, 2, 20, 0));
        executorService.submit(() -> service.ecriture(4, 2, 20, 1));
        executorService.submit(() -> service.lecture(5, 7, 8));
        executorService.submit(() -> service.lecture(9, 10, 8));

        utils.sleep(13000);
        executorService.submit(() -> service.lecture(6, 1, 1));        
        // On attend leur fin.
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        service.lecture(7, 0, 0);
        utils.getLog().forEach(l -> System.out.println(l));
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}