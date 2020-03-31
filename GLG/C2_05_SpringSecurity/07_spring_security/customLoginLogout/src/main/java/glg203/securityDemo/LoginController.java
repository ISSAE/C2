package glg203.securityDemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "monLogin";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "monLogin";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}