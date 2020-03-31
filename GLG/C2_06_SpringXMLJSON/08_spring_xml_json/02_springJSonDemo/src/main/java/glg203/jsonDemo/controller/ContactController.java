package glg203.jsonDemo.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import glg203.jsonDemo.model.Contact;
import glg203.jsonDemo.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ContactController
 */
@Controller
public class ContactController {
    @Autowired
    private ContactService service;

    @GetMapping(value = "/contact/{id}")
    @ResponseBody
    public Contact getContact(@PathVariable Long id) {
        return service.getContactParId(id);
    }

    /**
     * Expédie un contact - en JSON ou XML - en mode POST, et l'affiche.
     * L'annotation "@RequestBody" précise que le corps de la requête
     * ne suit pas le codage usuel de http (les paramètres), mais est le codage 
     * d'un élément dans un des formats acceptés par consumes (et compris par un MessageConverter)
     * @return
     * @throws IOException
     */
    @PostMapping(path = "/contact", consumes = {"application/json", "application/xml"})
    public void sendContact(@RequestBody Contact contact, Writer out, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        out.write(contact.toString());        
    }
    
}