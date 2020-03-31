package glg203.jsonDemo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Contact.
 * Doit avoir un constructeur par défaut, ou il faut créer un XmlAdapter.
 * Voir https://stackoverflow.com/questions/4387296/jaxb-and-constructors
 */
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Contact {
    private Long id;
    private String nom;
    private String adresse;
    private List<Telephone> numeros;
    
    private Contact() {        
    }

    public Contact(Long id, String nom, 
        String adresse, List<Telephone> numeros) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.numeros = numeros;
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

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Telephone> getNumeros() {
        return this.numeros;
    }

    public void setNumeros(List<Telephone> numeros) {
        this.numeros = numeros;
    }
  

}