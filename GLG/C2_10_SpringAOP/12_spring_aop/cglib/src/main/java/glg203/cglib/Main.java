package glg203.cglib;

import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        try (PrintWriter print = new PrintWriter(System.out)) {
            Compteur c = LoggerFactory.creerLogger(new Compteur(), print);
            c.setValeur(20);
            c.incr();
            int v = c.getValeur();
            print.println("Résultat " + v + " (21 attendu)");
        }
    }
}
