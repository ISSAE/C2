package glg203.rest.modele;

/**
 * Contact
 */
public class Contact {

    private String nom;
    private String telephone;

    public Contact() {        
    }

    public Contact(String nom, String telephone) {
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
            " nom='" + getNom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            "}";
    }

}