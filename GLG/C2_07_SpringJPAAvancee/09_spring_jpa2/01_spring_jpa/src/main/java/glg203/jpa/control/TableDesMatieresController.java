package glg203.jpa.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import glg203.jpa.service.DemoAnnotationsAvanceesService;

/**
 * TableDesMatieresController
 */
@Controller
@RequestMapping("/tableMatieres")
public class TableDesMatieresController {
    
    @Autowired
    DemoAnnotationsAvanceesService service;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("liste", service.tablesDesMatieres());
        return "tableMatieres/liste";
    }

    @PostMapping("/modifier")
    public String liste() {
        service.ajouteEntreeATableDesMatieres();
        return "redirect:/tableMatieres";
    }



    
}