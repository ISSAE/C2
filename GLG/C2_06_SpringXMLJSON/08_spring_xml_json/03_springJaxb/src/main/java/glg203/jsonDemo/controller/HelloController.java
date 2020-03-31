package glg203.jsonDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HelloController
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String demo() {
        return "salut";
    }
    
}