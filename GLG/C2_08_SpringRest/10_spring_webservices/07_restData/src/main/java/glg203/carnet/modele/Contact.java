package glg203.carnet.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Contact
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue
    Long id;

    private String nom;
    private String prenom;

    /**
     * Les données à propos du contact.
     */
    @ElementCollection
    @OrderColumn
    List<Entree> entrees;
    

    Contact() {
    }

    public Contact(Long id, String nom, String prenom, List<Entree> entrees) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.entrees = entrees;
    }


    public Contact(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.entrees = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }

    void setId(Long id) {
        this.id = id;
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

    public List<Entree> getEntrees() {
        return this.entrees;
    }

    public void setEntrees(List<Entree> entrees) {
        this.entrees = entrees;
    }

    public void addEntree(TypeEntree type, String valeur) {
        this.entrees.add(new Entree(type, valeur));
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", entrees='" + getEntrees() + "'" +
            "}";
    }
    
}