package glg203.jsonDemo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contact
 */
public class Contact {
    private Long id;
    private String nom;
    private String adresse;
    private List<Telephone> numeros;

    @JsonCreator
    public Contact(@JsonProperty("id") Long id,
                @JsonProperty("nom") String nom,
                @JsonProperty("adresse") String adresse, 
                @JsonProperty("numeros") List<Telephone> numeros) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;        
        //this.numeros = (numeros == null) ? new ArrayList<>(): numeros;
        this.numeros = numeros;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Telephone> getNumeros() {
        return this.numeros;
    }

    public void setNumeros(List<Telephone> numeros) {
        this.numeros = numeros;
    }

    /**
     * Cette méthode sert pour le test, mais elle n'a rien à voir avec JSON !
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id).append(" ")
            .append(nom).append('\n')
            .append(adresse).append('\n');
        for (Telephone telephone : numeros) {
            builder.append(telephone).append('\n');
        }
        return builder.toString();
    }

}