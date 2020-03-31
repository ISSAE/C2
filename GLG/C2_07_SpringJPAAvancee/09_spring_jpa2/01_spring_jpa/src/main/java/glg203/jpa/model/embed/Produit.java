package glg203.jpa.model.embed;

import javax.persistence.*;

/**
 * Produit
 */
@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String designation;

    private double prix;


    Produit() {
    }

    public Produit(Long id, String designation, double prix) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
    }

    public Produit(String designation, double prix) {
        this.designation = designation;
        this.prix = prix;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Produit id(Long id) {
        this.id = id;
        return this;
    }

    public Produit designation(String designation) {
        this.designation = designation;
        return this;
    }

    public Produit prix(double prix) {
        this.prix = prix;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", prix='" + getPrix() + "'" +
            "}";
    }

}