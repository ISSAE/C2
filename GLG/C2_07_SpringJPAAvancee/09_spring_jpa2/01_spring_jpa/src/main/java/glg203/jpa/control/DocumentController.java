package glg203.jpa.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import glg203.jpa.service.DocumentService;

/**
 * DocumentController.
 * Les documents montrent le codage de l'héritage.
 */
@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("liste", documentService.documentsDTO());
        model.addAttribute("dernier", documentService.dernier());
        return "document/liste";
    }
}