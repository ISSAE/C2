package glg203.restserveur;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import glg203.rest.dto.ContactDTO;
import glg203.rest.modele.Contact;

/**
 * ContactService
 */
@Service
public class ContactService {

    private AtomicLong maxId = new AtomicLong(0);

    private Map<Long, Contact> contacts = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        synchronized (contacts) {
        }
        for (int i = 0; i < 25; i++) {
            long id = maxId.incrementAndGet();
            contacts.put(id, new Contact(id, "n" + i, "tel" + i));
        }
    }

    /**
     * Retourne une copie des contacts
     * 
     * @return
     */
    public List<Contact> findAll() {
        ArrayList<Contact> result = new ArrayList<>();
        synchronized (contacts) {
            result = new ArrayList<>(contacts.values());
        }
        return result;
    }

    public Contact getContact(long id) {
        Contact res;
        synchronized (contacts) {
            res = contacts.get(id);
        }
        return res;
    }

    /**
     * Dit si un contact est en ligne. (en fait, renvoie true ou false selon
     * l'heure)
     */
    public boolean isAvailable(long id) {
        return (id % 6 == (LocalTime.now().getMinute() / 10));
    }

    /**
     * Ajoute un nouveau contact (dont l'Id est au départ null)
     * <p>
     * Post-condition : le contact a un id.
     * 
     * @param contact
     */
    public void addContact(Contact contact) {
        synchronized (contact) {
            Long id = maxId.incrementAndGet();
            contact.setId(id);
            contacts.put(id, contact);
        }
    }

}