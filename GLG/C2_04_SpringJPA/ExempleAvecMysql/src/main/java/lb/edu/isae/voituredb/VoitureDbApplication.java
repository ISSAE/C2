package lb.edu.isae.voituredb;

import lb.edu.isae.voituredb.domain.Proprietaire;
import lb.edu.isae.voituredb.domain.Voiture;
import lb.edu.isae.voituredb.domain.ProprietaireRepository;
import lb.edu.isae.voituredb.domain.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class VoitureDbApplication {

    @Autowired
    private VoitureRepository repository;

    @Autowired
    private ProprietaireRepository orepository;

    public static void main(String[] args) {
        SpringApplication.run(VoitureDbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Proprietaire owner1 = new Proprietaire("John", "Johnson");
            Proprietaire owner2 = new Proprietaire("Mary", "Robinson");
            orepository.save(owner1);
            orepository.save(owner2);

            repository.save(new Voiture("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owner1));
            repository.save(new Voiture("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owner2));
            repository.save(new Voiture("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000, owner2));
        };
    }
}
