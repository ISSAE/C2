package glg203.web01;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HelloController
 */
@Controller
public class HelloController {

    @GetMapping("/hello/spring")
    public void hello(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().println("Bonjour depuis un contrôleur Spring.");
    }
}