package glg203.restserveur;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import glg203.rest.modele.Contact;

/**
 * ContactController
 */
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    ContactService service;

    @GetMapping
    public ArrayList<Contact> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Contact get(@PathVariable int id) {
        return service.getContact(id);
    }

    @PostMapping
    @ResponseBody
    public Contact post(@RequestBody Contact contact) {
        service.addContact(contact);
        return contact;
    }

}