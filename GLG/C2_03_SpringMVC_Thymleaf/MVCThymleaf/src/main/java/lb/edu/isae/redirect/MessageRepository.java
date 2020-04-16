package lb.edu.isae.redirect;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Une liste (thread safe) de messages...
 * Simule par exemple une base de données...
 */
@Component
public class MessageRepository {
	
	/**
	 * Type bien adapté quand plus de lectures que d'écriture...
	 * Sinon, on peut utiliser Vector.
	 * à noter: l'itération n'est pas thread safe, mais
	 * ça n'est pas dangereux ici.
	 */
	private CopyOnWriteArrayList<Message> messages= new CopyOnWriteArrayList<>();

        
	@PostConstruct
	private void init() {
		add("premier message");
		add("second message");
	}

	public void add(String message) {
		messages.add(new Message(message));
	}

	public Collection<Message> getMessages() {
		return Collections.unmodifiableCollection(messages);
	}

	public Message getMessage(int pos) {
		return messages.get(pos);
	}
}
