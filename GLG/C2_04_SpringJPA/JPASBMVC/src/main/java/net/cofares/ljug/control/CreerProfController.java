package net.cofares.ljug.control;


import net.cofares.ljug.control.form.ProfForm;
import net.cofares.ljug.service.ProfService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * CreerProfController
 */
@Controller
@RequestMapping("/prof/creer")
public class CreerProfController {    
    private final ProfService profService;

    public CreerProfController(ProfService profService) {
        this.profService = profService;
    }
    @GetMapping
    public String get(Model model) {
        model.addAttribute(new ProfForm());
        return "prof/form";
    }

    @PostMapping
    public String post(ProfForm form, 
        Model model) {
        // Nous laissons les vérifications comme exercice au lecteur
        // intéressé.
        profService.creerProf(form.getNom(), form.getPrenom());
        return "redirect:/prof";
    }
    
}