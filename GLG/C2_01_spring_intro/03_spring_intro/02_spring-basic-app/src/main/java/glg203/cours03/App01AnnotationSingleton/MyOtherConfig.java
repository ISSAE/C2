package glg203.cours03.App01AnnotationSingleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyOtherConfig
 */
@Configuration
public class MyOtherConfig {

    @Bean UnBean unBean() {
        return new UnBean();
    }

    @Bean BeanClient clientA() {
        return new BeanClient(unBean());
    }
    
    @Bean BeanClient clientB() {
        return new BeanClient(unBean());
    }
    
    @Bean TwoClients twoClients() {
        return new TwoClients(clientA(), clientB());
    }
}