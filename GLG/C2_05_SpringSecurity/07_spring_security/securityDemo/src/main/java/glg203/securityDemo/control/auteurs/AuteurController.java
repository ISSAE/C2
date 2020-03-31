package glg203.securityDemo.control.auteurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import glg203.securityDemo.services.AuteurService;

/**
 * Contrôleur pour les actions simples sur les auteurs.
 */
@Controller
@RequestMapping("/admin/auteur")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    @GetMapping()
    public String listeAuteurs(Model model) {
        model.addAttribute("auteurs", auteurService.auteurs());
        return "auteur/liste";
    }
    
}