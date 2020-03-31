package glg203.rest.dto;

import org.springframework.hateoas.RepresentationModel;

import glg203.rest.modele.Contact;

/**
 * ContactDTO.
 * 
 * Autre option: n'utiliser que Contact, mais l'encapsuler dans un EntityModel.
 */

public class ContactDTO extends RepresentationModel<ContactDTO> {
    private Long id;
    private String nom, telephone;


    public ContactDTO() {
    }

    public ContactDTO(Contact contact) {
        this.id = contact.getId();
        this.nom = contact.getNom();
        this.telephone = contact.getTelephone();
    }

    public ContactDTO(String nom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }

    public ContactDTO(Long id, String nom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public ContactDTO nom(String nom) {
        this.nom = nom;
        return this;
    }

    public ContactDTO telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactDTO id(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            "}";
    }

    
}