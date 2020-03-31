package glg203.jpa.control;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import glg203.jpa.service.ForumService;

/**
 * ForumController
 */
@Controller
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("liste", service.findAll());
        return "/forum/liste";
    }


    /**
     * Crée un nouveau forum, avec un message de bienvenue.
     * (c'est ça qui montre l'usage de cascade.create)
     */
    @PostMapping("/creer")
    public String creerForum() {
        service.creerForumEtMessage("Un forum "+ new Date(), "Bienvenue !");
        return "redirect:/forum";
    }

    /**
     * Détruire un forum dont on passe l'ID.
     * @param id
     * @return redirection vers la page d'accueil.
     */
    @PostMapping("/detruire")
    public String detruireForum(Long id) {
        service.supprimerForum(id);
        return "redirect:/forum";
    }

    /**
     * Ajoute un message à un forum.
     * @param id
     * @return
     */
    @PostMapping("/ajouterMessage")
    public String ajouterMessage(Long id) {
        service.ajouterMessage(id, "message à "+ new Date());
        return "redirect:/forum";
    }



    
}