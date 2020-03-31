package glg203.jpa03;

/**
 * Main. Lancement du programme... glouton ou non.
 * 
 * Argument : "glouton", "paresseux".
 */
public class Main {

    public static void usage() {
        System.err.println("Erreur : donner un argument :");
        System.err.println("\t'paresseux' : évaluation paresseuse, n+1 select");
        System.err.println("\t'glouton' : évaluation gloutonne, 1 select");

    }

    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
        }
        switch (args[0]) {
        case "paresseux":
            NplusUnSelect.run();
            break;
        case "glouton":
            SelectGlouton.run();
            break;
        default:
            usage();
            break;
        }
    }
}