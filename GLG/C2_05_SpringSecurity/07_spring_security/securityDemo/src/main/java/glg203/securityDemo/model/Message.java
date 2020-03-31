package glg203.securityDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Un message sur le forum.
 */
@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	private String titre, texte;

	@ManyToOne
	private Auteur auteur;

	public Message(String titre, String texte, Auteur auteur) {
		this.titre = titre;
		this.texte = texte;
		this.auteur = auteur;
	}

	protected Message() {
	}

	public Long getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public Auteur getAuteur() {
		return auteur;
	}
}
