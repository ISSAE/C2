package glg203.jpa03.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Cours.
 * Un cours est suivi par des étudiants.
 */
@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @ManyToMany
    private Set<Etudiant> etudiants= new HashSet<>();

    protected Cours() {        
    }

    public Cours(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    /**
     * @return the etudiants
     */
    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void addEtudiant(Etudiant e) {
        etudiants.add(e);
    }
    
}