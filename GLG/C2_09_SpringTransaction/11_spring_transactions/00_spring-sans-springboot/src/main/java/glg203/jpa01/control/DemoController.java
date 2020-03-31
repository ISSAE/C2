package glg203.jpa01.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import glg203.jpa01.service.PersonneService;


/**
 * DemoController
 */
@Controller
public class DemoController {

    @Autowired
    PersonneService service;
    

    @GetMapping("/")
    public String index() {       
        return "index";        
    }
    
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", service.findAll());
        return "list";        
    }
    

    @PostMapping(value="/creerN")
    public String postMethodName(int n) {                
        service.creerNPersonnes(n);
        return "redirect:/";
    }

    @GetMapping(value="/creer")
    public String getPost() {       
        return "form";
    }
    
    @PostMapping(value="/creer")
    public String creerPost(String nom, String prenom) {
        service.creerPersonne(nom, prenom);
        return "redirect:/";
    }
    
}