package glg203.isolation;

/**
 * Utils
 */
public class Utils {

    
    public static void display(String toDisplay) {
        String line = "***************************************************************";
        System.out.println(line);
        System.out.println("*");
        System.out.println("*  "+ toDisplay );
        System.out.println("*");
        System.out.println(line);                
    }


    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {          
            // NE RIEN FAIRE.
        }
    }
}