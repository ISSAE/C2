package glg203.cours03.app04Scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Une démo de base de l'injection de dépendances dans Spring...
 */
public class App {

    public static void main(String[] args) {
        // ApplicationContext ne permet pas de fermer la ressource...
        try (AnnotationConfigApplicationContext ctx = 
                new AnnotationConfigApplicationContext(MyOtherConfig.class)) {
           DemoCompteur demoCompteur = ctx.getBean(DemoCompteur.class);
           demoCompteur.infos();
        }
    }
}
