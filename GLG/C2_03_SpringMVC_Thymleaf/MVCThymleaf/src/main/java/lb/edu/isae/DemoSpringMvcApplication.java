package lb.edu.isae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoSpringMvcApplication {
    /**
     * Un bean (par méthode) pour montrer que ceux-ci sont accessibles depuis
     * les contrôleurs.
     *
     * @return un HelloMessageGlobal
     */
    @Bean
    HelloMessageGlobal helloMessageGlobal() {
        return new HelloMessageGlobal("Ce message a été injecté");
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringMvcApplication.class, args);
    }

}
