package glg203.aop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Personne
 */
@Entity
public class Personne {

    @Id
    @GeneratedValue
    Long id;

    private String nom;

    public Personne() {
    }

    public Personne(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }


    public Personne(String nom) {      
        this.nom = nom;
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

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            "}";
    }
}