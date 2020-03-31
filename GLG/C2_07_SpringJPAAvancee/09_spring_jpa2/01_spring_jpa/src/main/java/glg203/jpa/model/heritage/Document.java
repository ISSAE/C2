package glg203.jpa.model.heritage;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue(value = "0")
@DiscriminatorColumn(name = "DTYPE",discriminatorType = DiscriminatorType.CHAR)
public abstract class Document {
    @Id
    @GeneratedValue
    private Long id;

    private String titre;

    @ManyToOne
    private Auteur auteur;


    Document() {
    }

    public Document(Long id, String titre, Auteur auteur) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
    }

    public Document(String titre, Auteur auteur) {        
        this.titre = titre;
        this.auteur = auteur;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Auteur getAuteur() {
        return this.auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Document id(Long id) {
        this.id = id;
        return this;
    }

    public Document titre(String titre) {
        this.titre = titre;
        return this;
    }

    public Document auteur(Auteur auteur) {
        this.auteur = auteur;
        return this;
    }

  

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titre='" + getTitre() + "'" +
            ", auteur='" + getAuteur() + "'" +
            "}";
    }

    public abstract <T> T accept(DocumentVisitor<T> visitor);
}