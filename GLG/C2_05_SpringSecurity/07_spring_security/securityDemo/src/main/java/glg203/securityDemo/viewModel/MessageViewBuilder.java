package glg203.securityDemo.viewModel;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import glg203.securityDemo.model.Message;
import glg203.securityDemo.roles.RolesRepository;
import glg203.securityDemo.viewModel.MessageView;

/**
 * Un builder pour construire les vues dont nous avons besoin.
 * <p> Remarque : ça n'est peut-être pas une si bonne idée. 
 * En effet, l'idée de savoir si oui ou non on peut détruire un message se retrouve étrangement placée.
 */
public class MessageViewBuilder {
   
    public static List<MessageView> getMessageViewsFor(Collection<Message> messages, UserDetails currentUser) {
        return messages.stream().map(m -> buildMessageView(m, currentUser)).collect(Collectors.toList());
    }

    private static MessageView buildMessageView(Message message, UserDetails currentUser) {
        MessageView view = new MessageView(message);
        if (currentUser != null) {
            if (currentUser.getAuthorities().contains(RolesRepository.ADMIN)) {
                view.setDestructible(true);
            } else if (message.getAuteur().getLogin().equals(currentUser.getUsername())) {
                view.setDestructible(true);
            }
        }
        return view;

    }

}