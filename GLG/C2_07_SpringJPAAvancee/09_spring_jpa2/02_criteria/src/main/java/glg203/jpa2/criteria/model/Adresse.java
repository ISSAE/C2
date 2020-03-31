package glg203.jpa2.criteria.model;

import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * Adresse
 */
@Embeddable
public class Adresse {
    private String rue;
    private String ville;


    public Adresse() {
    }

    public Adresse(String rue, String ville) {
        this.rue = rue;
        this.ville = ville;
    }

    public String getRue() {
        return this.rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Adresse rue(String rue) {
        this.rue = rue;
        return this;
    }

    public Adresse ville(String ville) {
        this.ville = ville;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Adresse)) {
            return false;
        }
        Adresse adresse = (Adresse) o;
        return Objects.equals(rue, adresse.rue) && Objects.equals(ville, adresse.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, ville);
    }

    @Override
    public String toString() {
        return "{" +
            " rue='" + getRue() + "'" +
            ", ville='" + getVille() + "'" +
            "}";
    }

}