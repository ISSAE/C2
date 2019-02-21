package dep4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * Décrivez votre classe Assembleur ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Assembleur
{
    

    /**
     * Constructeur d'objets de classe Assembleur
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public Assembleur() throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
      Properties props = new Properties();
      props.load(new FileReader("assembleur.props"));
      Class<?> c = Class.forName(props.getProperty("implementation_i_class"));
      I i = (I) c.newInstance();
      A a = new A(i);
    }

    static String getWoekingDir() {
        return System.getProperty("user.dir");
    }
    static String getProperty() throws FileNotFoundException, IOException
    {
        Properties props = new Properties();
        props.load(new FileReader("dep4/assembleur.props"));
        return props.getProperty("implementation_i_class");
    }
    
    public static A assembler() throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
       Class<?> c = Class.forName(getProperty());
      I i = (I) c.newInstance();
      return new A(i); 
    }
    
    

}
