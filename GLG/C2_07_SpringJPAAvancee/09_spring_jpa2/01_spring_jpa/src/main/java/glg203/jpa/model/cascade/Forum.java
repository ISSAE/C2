package glg203.jpa.model.cascade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

/**
 * Un forum a un titre et contient une liste de messages.
 */
@Entity
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titre;
    
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL)
    // ALL = PERSIST, MERGE, REMOVE, REFRESH, DETACH
    @OrderColumn  // On se rappelle la position des messages...
    private List<Message> messages = new ArrayList<>();
    

    public Forum() {
    }


    public Forum(String titre) {       
        this.titre = titre;       
    }

    public Forum(Long id, String titre, List<Message> messages) {
        this.id = id;
        this.titre = titre;
        this.messages = messages;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titre='" + getTitre() + "'" +
            ", messages='" + getMessages() + "'" +
            "}";
    }


    /**
     * Crée un nouveau message dans ce forum.
     * Attention, c'est bien cette méthode qu'il faut utiliser pour 
     * créer de nouveaux messages.
     * @param texteMessage
     */
	public void creerMessage(String texteMessage) {
        Message message = new Message(texteMessage, this);
        messages.add(message);
	}

}