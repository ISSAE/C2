package introspection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Démonstration de l'introspection.
 */
public class App {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // On récupère la classe Personne (dont on ne dépend potentiellement pas...)
        System.out.println("Explore une classe en en créant une instance avec le constructeur par défaut,");
        System.out.println("Puis en en affichant les champs (avec les getters)");
        Class<?> clazz = Class.forName("introspection.Personne");
        // On crée un objet personne, avec le constructeur par défaut :
        Object personne = clazz.newInstance();
        // On liste les méthodes, repère les getters, et on les utilise pour afficher
        // notre objet :
        for (Method m : clazz.getMethods()) {
            if (m.getName().startsWith("get") && m.getParameterCount() == 0) {
                String propriete = m.getName().substring(3);
                Object res = m.invoke(personne);
                System.out.println("propriete "+ propriete + " : " + res);
            }
        }        
    }
}
