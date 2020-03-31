package glg203.restserveur;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import glg203.rest.modele.Contact;

/**
 * ContactService
 */
@Service
public class ContactService {

    private ArrayList<Contact> contacts= new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i= 0; i < 5; i++) {
            contacts.add(new Contact("n"+i, "tel"+i));
        }
    }
    
    public ArrayList<Contact> findAll() {
        return contacts;
    }

    public Contact getContact(int pos) {
        return contacts.get(pos);
    }

	public void addContact(Contact contact) {
        contacts.add(contact);
	}
    
}