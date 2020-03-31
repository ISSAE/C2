package glg203.jsonDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import glg203.jsonDemo.model.Contact;
import glg203.jsonDemo.service.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * ContactController
 */
@Controller
public class ContactController {
    @Autowired
    private ContactService service;
    
    @GetMapping(value="/contact/{id}")
    @ResponseBody
    public Contact getMethodName(@PathVariable Long id) {
        return service.getContactParId(id);
    }
    
}