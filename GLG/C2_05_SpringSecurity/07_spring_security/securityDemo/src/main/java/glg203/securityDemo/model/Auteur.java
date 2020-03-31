package glg203.securityDemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Un auteur de message (et un utilisateur potentiellement connecté...)
 */
@Entity
public class Auteur {
    @Id
    private String login;
    private String password;
    private String signature;
    private boolean admin = false;

    public Auteur(String login, String password, String signature, boolean admin) {
        this.login = login;
        this.password = password;
        this.signature = signature;
        this.admin = admin;
    }

    protected Auteur() {}
    
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getSignature() {
        return signature;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
