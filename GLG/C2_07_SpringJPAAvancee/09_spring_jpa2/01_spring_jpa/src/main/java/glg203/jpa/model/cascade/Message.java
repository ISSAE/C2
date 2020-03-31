package glg203.jpa.model.cascade;

import javax.persistence.*;

/**
 * Un message dans un forum.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;    
    private String texte;    
    @ManyToOne
    private Forum forum;


    public Message() {
    }

    public Message(Long id, String texte, Forum forum) {
        this.id = id;
        this.texte = texte;
        this.forum = forum;
    }


    public Message(String texte, Forum forum) {
        this.texte = texte;
        this.forum = forum;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexte() {
        return this.texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Forum getForum() {
        return this.forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

 

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", texte='" + getTexte() + "'" +
            ", forum='" + getForum() + "'" +
            "}";
    }    
}