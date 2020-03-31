package glg203.jsonDemo.model;

/**
 * Personne
 */
public class Personne {

    private String nom, prenom;


    public Personne() {    
    }


    public Personne(String prenom, String nom) {
        this.nom = nom;
        this.prenom = prenom;
    }


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}