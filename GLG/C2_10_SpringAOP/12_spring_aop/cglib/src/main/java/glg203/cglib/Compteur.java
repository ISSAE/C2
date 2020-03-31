package glg203.cglib;

/**
 * Compteur
 */
public class Compteur {

    private int valeur = 0;

    public void setValeur(int v) {
        this.valeur = v;        
    }

    public void incr() {
        this.valeur++;
    }

    public int getValeur() {
        return valeur   ;
    }
}