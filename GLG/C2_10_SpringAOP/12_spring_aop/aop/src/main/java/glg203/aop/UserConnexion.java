package glg203.aop;

import org.springframework.stereotype.Component;

/**
 * Connexion d'un utilisateur (simulation simpliste de login)
 */
@Component
public class UserConnexion {

    private String userLogin;

    public  void login(String user) {
        this.userLogin = user;        
    }

    public void logout() {
        userLogin = null;
    }

    public boolean estConnecte() {
        return userLogin != null;
    }

    
}