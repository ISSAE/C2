package glg203.securityDemo.control.messages;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import glg203.securityDemo.control.messages.form.CreerMessageForm;
import glg203.securityDemo.services.ForumService;

/**
 * CreerMessageController
 */
@Controller
@RequestMapping("/message/creer")
@Secured({"ROLE_USER"})
public class CreerMessageController {

    @Autowired
    ForumService forumService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("form", new CreerMessageForm());
        return "/message/creerForm";
    }

    @PostMapping
    public String post(CreerMessageForm form, Principal principal) {
        forumService.sauverMessage(form.getTitre(), form.getTexte(), principal.getName());    
        return "redirect:/";
    }
}