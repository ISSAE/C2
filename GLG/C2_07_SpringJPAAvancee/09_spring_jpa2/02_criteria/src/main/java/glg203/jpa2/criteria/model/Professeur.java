package glg203.jpa2.criteria.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Professeur
 */
@Entity
public class Professeur extends Personne{

    @ManyToOne
    Universite universite;

    Professeur() {    
    }

    public Professeur(String nom, String prenom, Adresse adresse, Universite universite) {
        super(nom, prenom, adresse);
        this.universite = universite;
    }

    public Professeur(Long id, String nom, String prenom, Adresse adresse, Universite universite) {
        super(id, nom, prenom, adresse);
        this.universite = universite;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
}