package glg203.cours03.app02Annotation;

import glg203.cours03.app02Annotation.beans.ISalueur;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Une démo de base de l'injection de dépendances dans Spring...
 */
public class App {

    public static void main(String[] args) {
        // ApplicationContext ne permet pas de fermer la ressource...
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class)) {
            // Le bean ci-dessous a été défini directement dans MyConfig
            ISalueur messager = ctx.getBean(ISalueur.class);
            messager.saluer();
        }
    }
}
