package glg203.securityDemo.control.messages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import glg203.securityDemo.model.Message;
import glg203.securityDemo.services.ForumService;
import glg203.securityDemo.viewModel.MessageView;
import glg203.securityDemo.viewModel.MessageViewBuilder;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    ForumService forumService;

    @GetMapping
    public String get(Model model, Authentication authentication) {
        // On désire indiquer au modèle s'il doit ou non
        // proposer un bouton de destruction de chaque message.
        // Pour cela, il faut savoir qui est l'utilisateur connecté...
        User user= null;
        if (authentication != null) {
            user = (User)authentication.getPrincipal();
            // attention, le Principal qu'on peut injecter et 
            // celui qu'on récupère ci-dessus sont... différents.
        }
        // Les données du modèle :
        List<Message> messages = forumService.listeMessages();
        // Mise en forme pour affichage :
        List<MessageView> messageViews = MessageViewBuilder.getMessageViewsFor(messages, user);
        model.addAttribute("liste",messageViews);
        return "/message/liste";
    }

    @PostMapping("/{id}/delete")
    public String deleteMessage(@PathVariable Long id) {
        forumService.findMessage(id).ifPresent(m ->
            forumService.deleteMessage(m)
        );
        return "redirect:/message";
    }
}
