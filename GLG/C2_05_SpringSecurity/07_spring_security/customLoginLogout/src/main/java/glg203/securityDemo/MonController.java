package glg203.securityDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MonController
 */
@Controller
public class MonController {

    @GetMapping("/protegee")
    @ResponseBody
    public String pageProtegee() {
        return "voici du contenu accessible seulement aux utilisateurs connectés";
    }

    @GetMapping("/")
    public String defaultPage() {
        return  "index";
    }
    
}