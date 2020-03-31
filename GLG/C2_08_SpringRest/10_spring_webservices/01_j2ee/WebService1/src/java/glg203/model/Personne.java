package glg203.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Personne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom, prenom;

    private String adresse;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "possesseur")    
    // inutile    ??
    //@XmlElementWrapper(name="tel")
    //@XmlElement(name="telephone")
    @ElementCollection
    @CollectionTable(name = "TELEPHONE", joinColumns = @JoinColumn(name = "PERSONNE_ID"))
    private Set<Telephone> telephones;

    public Personne() {
        this.telephones = new HashSet<Telephone>();
    }

    public Personne(String nom, String prenom, String adresse) {
        this.telephones = new HashSet<Telephone>();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    //@XmlTransient
    public Set<Telephone> getTelephones() {
        return telephones;
    }
//    

    public void addTelephone(TypeTelephone type, String num) throws TelephoneMalFormeException {
        Telephone tel = new Telephone(type, num);
        //tel.setPossesseur(this);
        telephones.add(tel);
    }

    public void setTelephones(Set<Telephone> telephones) {
        System.err.println("Setting telephones " + telephones);
        telephones = new HashSet<>(telephones);
        this.telephones.clear();
        this.telephones.addAll(telephones);
    }

}
