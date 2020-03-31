package glg203.json.model.bidi;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Etudiant
 */
public class Etudiant {
    private String nom="";

    @JsonBackReference
    private Universite universite;

    public Etudiant() {
        super();
    }

    public Etudiant(String nom) {
        this.nom = nom;
    }
    


    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Universite getUniversite() {
        return this.universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public Etudiant(String nom, Universite universite) {
        this.nom = nom;
        this.universite = universite;
    }
    
}