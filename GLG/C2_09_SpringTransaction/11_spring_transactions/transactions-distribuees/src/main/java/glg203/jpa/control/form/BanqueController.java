package glg203.jpa.control.form;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import glg203.jpa.dto.CompteDTO;
import glg203.jpa.model.CompteException;
import glg203.jpa.service.BanqueService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * BanqueController
 */
@Controller
public class BanqueController {

    @Autowired
    private BanqueService service;

    @GetMapping("/")
    public String root(Model model) {
        service.init();
        List<CompteDTO> comptes = service.getComptes();
        model.addAttribute("comptes", comptes);
        return "index";
    }

    @PostMapping(value="/virer")
    public String virer(double montant, RedirectAttributes redirectAttributes) {     
        try {   
            service.virer(montant);
        } catch (CompteException e) {
            // On pourrait ne pas faire de redirection ici, 
            // c'est juste histoire de montrer l'utilisation de RedirectAttributes
            redirectAttributes.addFlashAttribute("message", "désolé, erreur dans l'opération");
        }
        return "redirect:/";        
    }
    

   


}