package glg203.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import glg203.jpa.model.cascade.Forum;
import glg203.jpa.repositories.ForumRepository;

/**
 * ForumService
 */
@Service
@Transactional
public class ForumService {

    @Autowired
    ForumRepository forumRepository;

    public List<Forum> findAll() {
        return forumRepository.findAll();
    }

    public void creerForumEtMessage(String titreForum, String texteMessage) {
        Forum forum = new Forum(titreForum);
        forum.creerMessage(texteMessage);
        forumRepository.save(forum);
    }

    public void ajouterMessage(Long forumId, String message) {
        forumRepository.findById(forumId).ifPresent(
            forum -> forum.creerMessage(message)
        );
    }
    
    public void supprimerForum(Long forumId) {
        forumRepository.deleteById(forumId);
    }

}