package glg203.sessions;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * DemoSessionController
 */
@Controller
@RequestMapping("/sessions")
public class DemoSessionController {

    @Autowired
    Compteur compteur;

    @GetMapping
    public String index(Model model, HttpSession session) {
        model.addAttribute(compteur);
        if (session.getAttribute("date") == null) {
            session.setAttribute("date", new Date());
        }
        System.err.println(session.getAttribute("date"));
        System.err.println("****" + session + "***");
        return "sessions/index";
    }

    @GetMapping("/arg")
    public String commeArgument(Model model,
        @SessionAttribute Date date) {
        System.err.println(date);
        model.addAttribute(compteur);
        return "sessions/index";
    }
}
