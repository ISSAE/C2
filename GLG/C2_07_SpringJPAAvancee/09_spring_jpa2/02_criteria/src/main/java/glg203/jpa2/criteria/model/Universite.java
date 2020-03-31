package glg203.jpa2.criteria.model;

import javax.persistence.*;

/**
 * Une Universite.
 * 
 * <p>Comporte des étudiants et des professeurs, et dispose d'un catalogue de cours.
 * Le système permet de gérer plusieurs universités.
 * 
 * <p> Note: l'université fonctionne comme une fabrique pour ses composants.
 */
@Entity
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    
    @Embedded
    private Adresse adresse;


    public Universite() {
    }

    public Universite(Long id, String nom, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }


    public Universite(String nom, Adresse adresse) {
        this.nom = nom;
        this.adresse = adresse;
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

    public Adresse getAdresse() {
        return this.adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Universite id(Long id) {
        this.id = id;
        return this;
    }

    public Universite nom(String nom) {
        this.nom = nom;
        return this;
    }

    public Universite adresse(Adresse adresse) {
        this.adresse = adresse;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            "}";
    }
    
    
}