package glg203.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

/**
 * MainConfig
 */
@Configuration
public class MainConfig {

    // /**
    //  * Pré-rempli la base.
    //  * Attention, ce système est a priori limité : il ne peut gérer correctement
    //  * les références entre objets (donc, on peut faire des arbres, mais pas de vrai graphe.)
    //  * @return
    //  */
    // @Bean
    // public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
    //     Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
    //     factory.setResources(new Resource[] { new ClassPathResource("client-data.json") });
    //     return factory;
    // }
    // Pas franchement utilisable en pratique : outre la limitation sur le format des objets,
    // le système crée les entrées à chaque lancement... d'où une duplication intempestive des données.
    
}