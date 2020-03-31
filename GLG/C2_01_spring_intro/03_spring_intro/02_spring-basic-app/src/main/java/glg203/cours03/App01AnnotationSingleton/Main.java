package glg203.cours03.App01AnnotationSingleton;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("*****************************");
        System.out.println("Dans cette application, nous montrons que les beans sont des singletons par défaut.");
        System.out.println("*****************************");
        try (AnnotationConfigApplicationContext ctx= new AnnotationConfigApplicationContext(MyOtherConfig.class)) {
            TwoClients topLevel = ctx.getBean(TwoClients.class);
            topLevel.affiche();
        }
    }
}