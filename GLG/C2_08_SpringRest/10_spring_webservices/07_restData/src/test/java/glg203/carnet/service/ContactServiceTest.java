package glg203.carnet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import glg203.carnet.modele.Contact;
import glg203.carnet.modele.Entree;

@SpringBootTest
@Transactional
public class ContactServiceTest {

    @Autowired
    ContactService contactService;

    @Test
    public void testLoad() {
        List<Contact> contacts = contactService.findAll();
        assertTrue(contacts.size() > 0, "pas de contact trouvé ?");
    }

    @Test
    public void testGraffion() {
        List<Contact> contacts = contactService.findByNom("Graffion");       
        assertEquals(1, contacts.size());
        Contact graffion = contacts.get(0);
        assertEquals("Graffion", graffion.getNom());
        assertEquals("Pascal", graffion.getPrenom());
        List<Entree> entrees = graffion.getEntrees();
        assertEquals(2, entrees.size());
        assertEquals("bureau", entrees.get(0).getTypeEntree().getLabel());
        assertEquals("3845", entrees.get(0).getValeur());
        assertEquals("mail", entrees.get(1).getTypeEntree().getLabel());
        assertEquals("pascal@cnam.net", entrees.get(1).getValeur());
    }
}
