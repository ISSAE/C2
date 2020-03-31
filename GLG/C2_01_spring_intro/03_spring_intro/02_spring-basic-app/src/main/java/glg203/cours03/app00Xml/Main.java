package glg203.cours03.app00Xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Une démo de base de l'injection de dépendances dans Spring...
 */
public class Main {

    public static void main(String[] args) {
        
        // ApplicationContext ne permet pas de fermer la ressource...
        try (ClassPathXmlApplicationContext  ctx = 
                new ClassPathXmlApplicationContext("conf/config.xml")) {
                    
                ISalueur salueur= (ISalueur)ctx.getBean("salueur");
                salueur.saluer();
        }
    }
}
