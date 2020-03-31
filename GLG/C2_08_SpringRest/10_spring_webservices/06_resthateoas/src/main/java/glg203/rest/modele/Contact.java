package glg203.rest.modele;



/**
 * Contact (entité)
 */
public class Contact {

    private Long id;
    private String nom;
    private String telephone;


    public Contact() {
    }


    /**
     * Crée un contact sans id fixé.
     * @param nom
     * @param telephone
     */
    public Contact(String nom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }
   
    public Contact(Long id, String nom, String telephone) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
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

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Contact id(Long id) {
        this.id = id;
        return this;
    }

    public Contact nom(String nom) {
        this.nom = nom;
        return this;
    }

    public Contact telephone(String telephone) {
        this.telephone = telephone;
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