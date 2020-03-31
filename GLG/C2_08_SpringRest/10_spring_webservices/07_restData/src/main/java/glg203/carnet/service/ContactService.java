package glg203.carnet.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import glg203.carnet.modele.Contact;
import glg203.carnet.modele.TypeEntree;
import glg203.carnet.repository.ContactRepository;
import glg203.carnet.repository.TypeEntreeRepository;

/**
 * ContactService
 */
@Service
@Transactional
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TypeEntreeRepository typeEntreeRepository;

    @PostConstruct
    public void init() {
        TypeEntree bureau = creer("bureau");
        TypeEntree domicile = creer("domicile");
        TypeEntree mail = creer("mail");
        TypeEntree adresse = creer("adresse");
        TypeEntree [] typeEntrees = {
            bureau, domicile, mail, adresse
        };
        for (TypeEntree typeEntree : typeEntrees) {
            typeEntreeRepository.save(typeEntree);
        }
        // Crée quelques contacts pour tester les affichages dès le début.
        Contact graffion = new Contact("Graffion", "Pascal");
        graffion.addEntree(bureau, "3845");
        graffion.addEntree(mail, "pascal@cnam.net");
        contactRepository.save(graffion);
        Contact rosmorduc = new Contact("Rosmorduc", "Serge");
        rosmorduc.addEntree(bureau, "8322");
        rosmorduc.addEntree(mail, "serge@cnam.net");
        rosmorduc.addEntree(adresse, "2 rue Conté");
        contactRepository.save(rosmorduc);
        for (int i=0; i < 100; i++) {
            Contact contact = new Contact("n"+i, "p"+i);
            contactRepository.save(contact);
        }
    }

    private TypeEntree creer(String label) {
        TypeEntree res = new TypeEntree(label);
        typeEntreeRepository.save(res);
        return res;
    }

    public void sauver(Contact contact) {
        contactRepository.save(contact);
    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public List<Contact> findByNom(String nom) {
        return contactRepository.findByNom(nom);
    }

}