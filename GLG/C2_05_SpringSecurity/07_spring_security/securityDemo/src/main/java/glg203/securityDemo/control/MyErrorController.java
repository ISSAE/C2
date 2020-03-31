package glg203.securityDemo.control;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MyErrorController :
 * affichage customisé (un tout petit peu)
 * des messages d'erreur.
 * 
 * <p> Ne fonctionne que si implémente ErrorController.
 * <p> Bien penser à ne pas gérer que GET : les erreurs ne sont pas
 * affichées par redirection.
 */
@Controller
public class MyErrorController implements ErrorController{

    @RequestMapping("/error")
    public String get() {
        return "erreur";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}