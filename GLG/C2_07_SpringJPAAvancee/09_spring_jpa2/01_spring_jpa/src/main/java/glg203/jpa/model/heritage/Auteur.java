package glg203.jpa.model.heritage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Auteur {
    @Id
    @GeneratedValue
    private Long id;
    
    private String nom, prenom;

    Auteur() {
    }

    public Auteur(Long id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }


    public Auteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Auteur id(Long id) {
        this.id = id;
        return this;
    }

    public Auteur nom(String nom) {
        this.nom = nom;
        return this;
    }

    public Auteur prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

  
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            "}";
    }


}