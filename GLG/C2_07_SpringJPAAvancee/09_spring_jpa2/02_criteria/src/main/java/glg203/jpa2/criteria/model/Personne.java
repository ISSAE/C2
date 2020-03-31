package glg203.jpa2.criteria.model;

import javax.persistence.*;

/**
 * Une Personne.
 * Cette classe n'est pas une entité. On l'utilise pour l'héritage,
 * car Prof et Etudiant ont des caractéristiques communes.
 * 
 * <p> En revanche, ces caractéristiques communes ne sont pas (ou peu)
 * exploitées par JPA. Une MappedSuperClass est techniquement une classe 
 * Java parente, mais cet héritage n'est pas utilisable dans JPA (on 
 * ne peut par exemple pas écrire <code>select p from Personne p</code>).
 */
@MappedSuperclass
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
  
    private String nom, prenom;

    @Embedded
    private Adresse adresse;

    public Personne() {
    }

    public Personne(Long id, String nom, String prenom, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Personne(String nom, String prenom, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", nom='" + nom + "'" +
            ", prenom='" + prenom + "'" +
            ", adresse='" + adresse + "'" +
            "}";
    }

}