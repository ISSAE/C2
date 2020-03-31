package glg203.json.model.bidi;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Universite.
 * 
 * <p>Invariant :  u.getEtudiants().contains(e) <=> e.getUniversite() = u
 */
public class Universite {

    private String nom= "";

    @JsonManagedReference
    private List<Etudiant> etudiants= new ArrayList<>();

    public Universite() {}

    public Universite(String nom, List<Etudiant> etudiants) {
        this.nom = nom;
        this.etudiants = etudiants;
    }

    public Universite(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Etudiant> getEtudiants() {
        return this.etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void addEtudiant(Etudiant e) {
        if (e.getUniversite() != null) {
            e.getUniversite().etudiants.remove(e);
        }
        this.etudiants.add(e);
        e.setUniversite(this);
    }
        


}