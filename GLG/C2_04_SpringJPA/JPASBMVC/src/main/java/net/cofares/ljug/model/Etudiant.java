package net.cofares.ljug.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

/**
 * Etudiant.
 * Cette classe n'est utilisée que dans les tests.
 */
@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom, prenom;

    private String addresse;

    @ManyToMany
    private Set<Cours> cours = new HashSet<>();
    

    public Etudiant() {    
    }

    public Etudiant(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    public String getAddresse() {
        return addresse;
    }

    /**
     * Retourne les cours de cet étudiant.     
     * @return the cours (unmodifiableSet)
     */
    public Set<Cours> getCours() {
        return Collections.unmodifiableSet(cours);
    }

    public void ajouterCours(Cours c) {
        cours.add(c);
    }

    public void supprimer(Cours c) {
        cours.remove(c);
    }

}