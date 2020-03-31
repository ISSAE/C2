package glg203.jpa03.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Etudiant
 */
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    /**
     * Constructeur à usage interne à JPA.
     */
    protected Etudiant() {}

    public Etudiant(String nom) {
        this.nom = nom;
    }

  
    public Long getId() {
        return id;
    }

  
    public String getNom() {
        return nom;
    }

   
    public void setNom(String nom) {
        this.nom = nom;
    }
}