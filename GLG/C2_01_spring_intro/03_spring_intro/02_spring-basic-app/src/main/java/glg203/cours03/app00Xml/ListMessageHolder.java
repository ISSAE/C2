package glg203.cours03.app00Xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Un message choisi aléatoirement dans une liste.
 */
public class ListMessageHolder implements IMessageHolder{

    private final List<String> messages;
    private final Random random= new Random();

    public ListMessageHolder(Collection<String> messages) {
        this.messages = new ArrayList<>(messages);
    }

    @Override
    public String getMessage() {
        int pos= random.nextInt(messages.size());
        return messages.get(pos) ;
    }
}