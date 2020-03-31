package glg203.jpa.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import glg203.jpa.service.DemoAnnotationsAvanceesService;

/**
 * FactureController.
 * Les facture montrent des maps codant les lignes de facture.
 */
@Controller
@RequestMapping("/facture")
public class FactureController {

    @Autowired
    private DemoAnnotationsAvanceesService embeddedService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("liste", embeddedService.factures());
        return "facture/liste";
    }

    @PostMapping("moinsUn")
    public String removeOne() {
        embeddedService.retirerUnProduitDeChaqueFacture();
        return "redirect:/facture";
    }



    
}