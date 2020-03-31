package glg203.xml.sax.model;

/**
 *
 * @author rosmord
 */
public interface NoeudVisiteur<T> {
    T visitElement(NoeudElement n);
    T visitTexte(NoeudTexte t);
}
