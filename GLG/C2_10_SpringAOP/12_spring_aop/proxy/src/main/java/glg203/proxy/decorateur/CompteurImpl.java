package glg203.proxy.decorateur;

/**
 * CompteurImpl
 */
public class CompteurImpl implements ICompteur{

    private int v = 0;
    @Override
    public void setValeur(int v) {
        this.v = v;
    }

    @Override
    public void incr() {
        v++;
    }

    @Override
    public int getValeur() {
        return v;
    }

    
}