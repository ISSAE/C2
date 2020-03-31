package glg203.cours03.app04Scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyOtherConfig {

    @Bean
    public Compteur compteurPartage() {
        return new Compteur();
    }

    @Bean
    @Scope("prototype")
    public Compteur compteurPrototype() {
        return new Compteur();
    }
    
    @Bean
    public DemoCompteur demoCompteur() {
        DemoCompteur res = new DemoCompteur();
        res.setCompteur1(compteurPartage());
        res.setCompteur2(compteurPartage());
        res.setCompteur3(compteurPrototype());
        res.setCompteur4(compteurPrototype());
        return res;
    }
    
    
    
    

}
