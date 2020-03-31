package glg203.cours03.app03DemoEvents;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * App
 */
@Configuration
@ComponentScan
public class App {

    public static void main(String[] args) {
        // ApplicationContext ne permet pas de fermer la ressource...
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class)) {
            Logiciel logiciel = ctx.getBean(Logiciel.class);
            System.out.println();
            System.out.println();
            System.out.println();
            logiciel.run();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
}