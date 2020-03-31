package glg203.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Compte
 */
@Entity
public class Compte {
    @Id
    String numero;

    private String client;

    private double solde;

    Compte() {}


    public Compte(String numero, String client, double solde) {
        this.numero = numero;
        this.client = client;
        this.solde = solde;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getClient() {
        return this.client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getSolde() {
        return this.solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void deposer(double montant) throws CompteException{
        if (montant < 0)
            throw new CompteException();
        // Pour déclencher un problème et voir que les transactions sont correctement réalisées :
        if (montant == 100)
            throw new CompteException();
        solde+= montant;        
    }

    public void retirer(double montant) throws CompteException{
        if (montant < 0 || montant > solde)
            throw new CompteException();
        solde -= montant;
    }

    public void virerVers(Compte autre, double montant) throws CompteException{
        this.retirer(montant);
        autre.deposer(montant);        
    }

    @Override
    public String toString() {
        return "{" +
            " numero='" + getNumero() + "'" +
            ", client='" + getClient() + "'" +
            ", solde='" + getSolde() + "'" +
            "}";
    }


    
}