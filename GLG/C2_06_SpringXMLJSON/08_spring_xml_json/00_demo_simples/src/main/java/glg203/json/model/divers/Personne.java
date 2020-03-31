package glg203.json.model.divers;


/**
 * Personne
 */
public class Personne {
    private String nom, prenom;
    private int age;

    public Personne() {        
    }

    public Personne(String nom, String prenom, int age) {
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