package lb.edu.isae.redirect;

/**
 * Un POJO Java Bean sans aucune annotation
 * @author Pascal Fares <pascal.fares at cofares.net>
 */
public class Message {
	private String texte = "";
	private String auteur = "inconnu";

	public Message(String texte) {
		this.texte = texte;
	}

	public Message() {
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
}


