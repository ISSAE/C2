package net.cofares.ljug.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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