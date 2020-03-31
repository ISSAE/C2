package glg203.isolation.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * La classe Compte, un grand classique pour nos exemples...
 */
@Entity
public class Compte {
    @Id         
    Long id;

    int solde;

    public Compte() {
    }

    public Compte(Long id, int solde) {
        this.id = id;
        this.solde = solde;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSolde() {
        return this.solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", solde='" + getSolde() + "'" +
            "}";
    }


    
}