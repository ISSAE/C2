package glg203.client;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.cnam.glg203.publications.*;

/**
 * ClientApp
 */
@SpringBootApplication
public class ClientApp {

    @Autowired
    PublicationsClient client;

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }

    @PostConstruct
    public void runMe() {
        client.creerPublication("Nouvelle publi "+ LocalDateTime.now(), "blah blah");
        ListePublicationsResponse listePublicationsResponse = client.listePublications();
        Publications liste = listePublicationsResponse.getListe();
        for (Publication p : liste.getPublications()) {
            System.out.println(p.getTitre());
        }
    }
  
}