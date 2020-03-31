package glg203.proxy.decorateur;

import java.io.PrintWriter;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        ICompteur c = LogDecoratorFactory.creerLogDecorator(new CompteurImpl(), new PrintWriter(System.out));
        c.setValeur(10);
        c.incr();
        int res= c.getValeur();
        System.out.println("Résultat final "+ res); // 11 attendu.
    }
}