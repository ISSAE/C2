package glg203.securityDemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import glg203.securityDemo.dao.AuteurRepository;
import glg203.securityDemo.dao.MessageRepository;
import glg203.securityDemo.model.Auteur;
import glg203.securityDemo.model.Message;

/**
 * accès aux principaux services du forum.
 */
@Service
@Transactional
public class ForumService {
    @Autowired
    AuteurRepository auteurRepository;

    @Autowired
    MessageRepository messageRepository;

    public Optional<Message> findMessage(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> listeMessages() {
        return messageRepository.findAllWithAuteur();        
    }

	public Long sauverMessage(String titre, String texte, String login) {
        // récupération de l'auteur
        Auteur auteur = auteurRepository.findById(login).get(); 
        // (Ou utilisation de map ou flatMap pour traiter l'Optionnal)
        Message message = new Message(titre, texte, auteur);
        messageRepository.save(message);
        return message.getId();        
    }
    
   

    /**
     * Détruit un message.
     * On passe un message et non pas simplement son ID parce qu'à moment donné, 
     * il faut bien connaître le propriétaire du message pour savoir si on peut le détruire.
     * @param m le message à détruire.
     */
    @PreAuthorize("isAuthenticated() and (hasRole('ADMIN') or #m.auteur.login == principal.username)")
	public void deleteMessage(Message m) {
		messageRepository.deleteById(m.getId());
	}

}