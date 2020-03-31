package glg203.json.model.bidiIdentity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Etudiant
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Etudiant {

    private Long id;

    private String nom="";

    private Universite universite;

    public Etudiant() {
        super();
    }

    public Etudiant(Long id, String nom) {
        this.id = id;
        this.nom = nom;
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
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}