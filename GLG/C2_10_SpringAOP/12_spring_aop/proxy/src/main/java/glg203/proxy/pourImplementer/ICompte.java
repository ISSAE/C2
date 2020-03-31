package glg203.proxy.pourImplementer;

/**
 * ICompte
 */
public interface ICompte {

    void setProprietaire(IPersonne p);
    IPersonne getProprietaire();
    void setSolde(double d);
    double getSolde();
    
}