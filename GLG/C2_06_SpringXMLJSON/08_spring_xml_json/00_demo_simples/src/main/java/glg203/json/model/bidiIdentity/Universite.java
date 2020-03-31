package glg203.json.model.bidiIdentity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Universite.
 * 
 * <p>Invariant :  u.getEtudiants().contains(e) <=> e.getUniversite() = u
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Universite {

    private Long id;
    
    private String nom= "";

    private List<Etudiant> etudiants= new ArrayList<>();

    public Universite() {}

    public Universite(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Universite(final String nom, final List<Etudiant> etudiants) {
        this.nom = nom;
        this.etudiants = etudiants;
    }

    public Universite(final String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public List<Etudiant> getEtudiants() {
        return this.etudiants;
    }

    public void setEtudiants(final List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void addEtudiant(final Etudiant e) {
        if (e.getUniversite() != null) {
            e.getUniversite().etudiants.remove(e);
        }
        this.etudiants.add(e);
        e.setUniversite(this);
    }
        

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}