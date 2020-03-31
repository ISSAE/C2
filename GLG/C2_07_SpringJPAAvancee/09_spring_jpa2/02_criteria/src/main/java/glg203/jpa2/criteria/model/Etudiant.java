package glg203.jpa2.criteria.model;

import javax.persistence.*;

/**
 * Etudiant
 */
@Entity
public class Etudiant extends Personne {

    @ManyToOne
    private Formation formation;

    protected Etudiant() {
    }

    public Etudiant(String nom, String prenom,Adresse adresse, Formation formation) {
        super(nom, prenom, adresse);
        this.formation = formation;
    }

    public Etudiant(Long id, String nom, String prenom, Adresse adresse, Formation formation) {
        super(id, nom, prenom, adresse);
        this.formation = formation;
    }


    public Formation getFormation() {
        return this.formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    @Override
    public String toString() {
        return "{" +
            " formation='" + getFormation() + "'" +
            "}";
    }
   
}