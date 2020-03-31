package glg203.securityDemo.control;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Ce contrôleur permet d'explorer diverses variations sur le système de
 * sécurité. Il n'est pas utilisé pour les "vraies" fonctionnalités de notre
 * système.
 */
@Controller
public class Demo {

    /**
     * Une page exclusivement visible par l'utilisateur anonyme. Sécurisation
     * par @Secured.
     * 
     * @return
     */
    @GetMapping("/anonyme")
    @ResponseBody
    @Secured({ "ROLE_ANONYMOUS" })
    public String pageAnonyme() {
        return "cette page est visible par un utilisateur de rôle 'ROLE_ANONYMOUS' et lui seul";
    }

    /**
     * Une page uniquement visible par l'admin. Sécurisée par les règles de
     * WebSecurityConfig.
     */
    @GetMapping("/admin/hello")
    @ResponseBody
    public String adminHello() {
        return "cette page n'est visible que par l'admin";
    }


     /**
     * Une page réservée aux utilisateurs connectés
     * WebSecurityConfig.
     */
    @GetMapping("/zoneConnectee")
    @ResponseBody
    public String zoneConnectee() {
        return "cette page n'est visible que par les utilisateurs connectés";
    }

    /**
     * Une page innaccessible (même par l'admin...) Le rôle qui lui correspond n'est
     * donné à personne. ça ne sert à rien, sinon à démontrer que @Secured
     * fonctionne.
     * 
     * @return
     */
    @GetMapping("/roleNonDonne")
    @ResponseBody
    @Secured({ "ROLE_INTERDIT" })
    public String roleNonDonne() {
        return "cette page n'est visible par personne !!!!";
    }

    /**
     * Une page accessible aux utilisateurs disposant du droit ROLE_USER. Soit :
     * tous les utilisateurs connectés (on s'est arrangé pour que ce soit le cas).
     * 
     * @return
     */
    @GetMapping("/roleUser")
    @ResponseBody
    @Secured({ "ROLE_USER" })
    public String roleUser() {
        return "cette page est visible en étant connecté !!!!";
    }

    /**
     * Démonstration de l'injection des objets "principal" et "authentication" dans un
     * contrôleur.
     * 
     * <p> Attention, l'objet "Principal" qu'on peut injecter n'est pas celui qu'on récupère avec
     * getPrincipal() sur l'authentication. C'est ce dernier qui est le plus pratique.
     */
    @GetMapping("/info")
    @ResponseBody
    public String infos(Principal principal, Authentication authentication) {
        StringBuilder builder = new StringBuilder();
        if (principal != null && authentication != null) {
            builder.append("<h1>****************** Credentials *****************</h1>").append("<br/>\n")
                    .append(authentication.getCredentials()).append("<br/>\n")
                    .append("<h1>****************** Details *****************</h1>").append("<br/>\n")
                    .append(authentication.getDetails()).append("<br/>\n")
                    .append("<h1>****************** Name *****************</h1>").append("<br/>\n")
                    .append(authentication.getName()).append("<br/>\n")
                    .append("<h1>****************** Principal *****************</h1>").append("<br/>\n")
                    .append(authentication.getPrincipal()).append("<br/>\n")
                    .append("<h1>****************** authenticated *****************</h1>").append("<br/>\n")
                    .append(authentication.isAuthenticated()).append("<br/>\n")
                    .append("<h1>****************** Authorities *****************</h1>").append("<br/>\n")
                    .append(authentication.getAuthorities()).append("<br/>\n").append("<br/>\n").append("<br/>\n")
                    .append("<h1>****************** Principal object name *****************</h1>").append("<br/>\n")
                    .append(principal.getName()).append("<br/>\n")
                    .append("<h1>****************** Principal object (brut de décoffrage) *****************</h1>")
                    .append("<br/>\n").append(principal).append("<br/>\n").append("<br/>\n");
            ;
        } else {
            builder.append(principal).append("<br/>").append(authentication);
        }

        return builder.toString();
    }

}