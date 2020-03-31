package glg203.securityDemo.control.auteurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import glg203.securityDemo.control.auteurs.form.AuteurCreerForm;
import glg203.securityDemo.services.AuteurService;

/**
 * Contrôleur du formulaire de création d'auteurs.
 */
@Controller
@RequestMapping("/admin/auteur/creer")
public class CreerAuteurController {

    @Autowired
    AuteurService auteurService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("form", new AuteurCreerForm());
        return "/auteur/creerForm";
    }

    @PostMapping
    public String post(AuteurCreerForm form) {
        System.err.println("ICI...");
        // Ajouter éventuellement la validation.
        auteurService.sauverAuteur(form.getLogin(), form.getPassword(), form.getSignature());
        return "redirect:/admin/auteur";
    }
}