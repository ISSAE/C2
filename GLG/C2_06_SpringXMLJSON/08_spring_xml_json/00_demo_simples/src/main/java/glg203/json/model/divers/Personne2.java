package glg203.json.model.divers;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Personne
 */
public class Personne2 {
    private String nom, prenom;
    private int age;
    
    @JsonCreator
    public Personne2(
        @JsonProperty("nom")String nom,
        @JsonProperty("prenom") String prenom, 
        @JsonProperty("age") int age) {
        this.nom= nom;
        this.prenom= prenom;
        this.age= age;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }
    
}