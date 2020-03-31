package glg203;

import org.springframework.stereotype.Component;


public class HelloMessageGlobal {
	private String texte;

	public HelloMessageGlobal(String texte) {
		this.texte = texte;
	}

	public String getTexte() {
		return texte;
	}
}
