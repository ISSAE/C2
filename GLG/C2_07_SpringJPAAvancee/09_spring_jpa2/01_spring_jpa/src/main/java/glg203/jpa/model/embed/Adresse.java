package glg203.jpa.model.embed;

import java.util.Objects;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Adresse {

    private String rue;
    private String ville;
    private String codePostal;
    
    // Note à propos de Jackson :
    // il est possible de ne pas avoir besoin de ces annotations à partir de Java 8,
    // en activant un module de Jackson :
    // https://stackoverflow.com/questions/21920367/why-when-a-constructor-is-annotated-with-jsoncreator-its-arguments-must-be-ann
    @JsonCreator
    public Adresse(
        @JsonProperty("rue")String rue,
        @JsonProperty("ville") String ville, 
        @JsonProperty("codePostal") String codePostal) {
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    /**
     * Pour JPA :-(.
     */
    private Adresse() {

    }

    public String getRue() {
        return this.rue;
    }

    public String getVille() {
        return this.ville;
    }

    public String getCodePostal() {
        return this.codePostal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Adresse)) {
            return false;
        }
        Adresse adresse = (Adresse) o;
        return Objects.equals(rue, adresse.rue) && Objects.equals(ville, adresse.ville) && Objects.equals(codePostal, adresse.codePostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, ville, codePostal);
    }

    @Override
    public String toString() {
        return "{" +
            " rue='" + getRue() + "'" +
            ", ville='" + getVille() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            "}";
    }

}