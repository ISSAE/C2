package glg203.xml.sax.model;

/**
 *
 * @author rosmord
 */
public abstract class Noeud {
    public abstract<T> T accept(NoeudVisiteur<T> v);
}
