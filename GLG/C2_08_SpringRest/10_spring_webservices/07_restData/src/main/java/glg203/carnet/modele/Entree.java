package glg203.carnet.modele;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Entree
 */
@Embeddable
public class Entree {
    @ManyToOne
    private TypeEntree typeEntree;
    private String valeur;


    public Entree() {
    }

    public Entree(TypeEntree typeEntree, String valeur) {
        this.typeEntree = typeEntree;
        this.valeur = valeur;
    }

    public TypeEntree getTypeEntree() {
        return this.typeEntree;
    }

    public String getValeur() {
        return this.valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Entree)) {
            return false;
        }
        Entree entree = (Entree) o;
        return Objects.equals(typeEntree, entree.typeEntree) && Objects.equals(valeur, entree.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeEntree, valeur);
    }

    @Override
    public String toString() {
        return "{" +
            " typeEntree='" + getTypeEntree() + "'" +
            ", valeur='" + getValeur() + "'" +
            "}";
    }

}