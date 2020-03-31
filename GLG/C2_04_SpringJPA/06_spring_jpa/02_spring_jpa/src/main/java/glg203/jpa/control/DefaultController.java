package glg203.jpa.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * DefaultController
 */
@Controller
public class DefaultController {
    @GetMapping(value = "/")
    public String menu() {
        return "menu";
    }

}