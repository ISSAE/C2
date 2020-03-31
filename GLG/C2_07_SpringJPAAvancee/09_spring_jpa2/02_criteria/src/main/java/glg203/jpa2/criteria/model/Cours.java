package glg203.jpa2.criteria.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

/**
 * Un cours.
 */
@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;

    @ManyToOne
    private Formation formation;

    @ManyToOne
    private Professeur responsable;

    @ElementCollection(targetClass=Theme.class)
    Set<Theme> themes= new TreeSet<>();
    
    protected Cours() {
    }

    public Cours(String titre, Formation formation, Professeur responsable) {
        this.titre = titre;
        this.formation = formation;
        this.responsable = responsable;
    }

    public Cours(Long id, String titre, Formation formation, Professeur responsable) {
        this.id = id;
        this.titre = titre;
        this.formation = formation;
        this.responsable = responsable;
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

    public Professeur getResponsable() {
        return this.responsable;
    }

    public void setResponsable(Professeur responsable) {
        this.responsable = responsable;
    }

    public Cours id(Long id) {
        this.id = id;
        return this;
    }

    public Cours titre(String titre) {
        this.titre = titre;
        return this;
    }

    public Cours responsable(Professeur responsable) {
        this.responsable = responsable;
        return this;
    }

    public Formation getFormation() {
        return formation;
    }

  
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Set<Theme> getThemes() {
        return themes;
    }

    public void addTheme(Theme theme) {
        themes.add(theme);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", titre='" + titre + "'" +
            ", formation='" + formation + "'" +
            ", responsable='" + responsable + "'" +
            ", themes='" + themes + "'" +
            "}";
    }    
}