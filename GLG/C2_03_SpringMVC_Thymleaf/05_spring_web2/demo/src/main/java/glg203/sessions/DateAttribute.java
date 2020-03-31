package glg203.sessions;

import java.util.Date;

/**
 * Un objet qui sera conservé comme attribut session.
 */
public class DateAttribute {
	Date dateCreation;

	public DateAttribute() {
		this.dateCreation = new Date();
	}

	@Override
	public String toString() {
		return dateCreation.toString();
	}
}
