package glg203.jpa.model.embed;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * LigneFacture.
 * Dans un premier temps, j'avais utilisé directement un Integer comme value de la map dans Facture.
 * Mais 
 */
@Embeddable
public class LigneFacture {
    @ManyToOne
    private Produit produit;
    private int quantite;

    public LigneFacture() {
    }

    public LigneFacture(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LigneFacture produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public LigneFacture quantite(int quantite) {
        this.quantite = quantite;
        return this;
    }

  
    @Override
    public String toString() {
        return "{" +
            " produit='" + getProduit() + "'" +
            ", quantite='" + getQuantite() + "'" +
            "}";
    }
    
}