package glg203.jpa.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import glg203.jpa.model.embed.Adresse;
import glg203.jpa.model.embed.Client;
import glg203.jpa.service.DemoAnnotationsAvanceesService;

/**
 * ClientController.
 * 
 * La classe Client montre l'usage de collection imbriquée.
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    DemoAnnotationsAvanceesService clientService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("listeClient", clientService.clients());
        return "client/liste";
    }

    @GetMapping("/test")
    public String creer() {
        Client client = new Client(200l, "Lovelace", new Adresse("rue Babbage", "Londres", "6768"));
        clientService.save(client);
        return "redirect:/client";
    }

    
    
}