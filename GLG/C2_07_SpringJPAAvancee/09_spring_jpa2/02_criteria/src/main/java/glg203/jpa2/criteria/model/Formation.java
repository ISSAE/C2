package glg203.jpa2.criteria.model;

import javax.persistence.*;

/**
 * Une formation à laquelle des étudiants peuvent s'inscrire.
 */
@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
  
    private String titre;

    @ManyToOne
    private Universite universite;
    

    protected Formation() {
    }

    public Formation(Long id, String titre, Universite universite) {
        this.id = id;
        this.titre = titre;
        this.universite = universite;
    }

    public Formation(String titre, Universite universite) {
        this.titre = titre;
        this.universite = universite;
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

    public Formation id(Long id) {
        this.id = id;
        return this;
    }

    public Formation titre(String titre) {
        this.titre = titre;
        return this;
    }

 
    public Universite getUniversite() {
        return universite;
    }

  
    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titre='" + getTitre() + "'" +
            "}";
    }

}