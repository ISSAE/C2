package glg203.cours03.app01Annotation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyConfig
 */
public class MyConfig {
    @Bean
    public IMessageHolder messageHolder() {
        return new HelloMessageHolder("depuis une application configurée par annotations");
    }    

    @Bean
    public ISalueur salueur() {
        return new SalueurImpl(messageHolder());
        // IMPORTANT : on appelle la méthode annotée @Bean, on 
        // ne fait pas un new explicitement sur l'argument.
    }
}