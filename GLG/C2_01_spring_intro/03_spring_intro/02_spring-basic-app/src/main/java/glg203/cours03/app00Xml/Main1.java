package glg203.cours03.app00Xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main1
 */
public class Main1 {

    public static void main(String[] args) {
        
        // ApplicationContext ne permet pas de fermer la ressource...
        try (ClassPathXmlApplicationContext  ctx = 
                new ClassPathXmlApplicationContext("conf/config1.xml")) {
                    
                ISalueur salueur= (ISalueur)ctx.getBean("salueur");
                salueur.saluer();
        }
    }
    
}