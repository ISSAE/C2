package lb.edu.isae.sessions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.io.PrintWriter;

@Controller
@SessionAttributes({"attributSimple"})
public class AutreSessionAttributeController {
	@GetMapping("/sessions/autre")
	public void autreSession(AttributSimple attributSimple, PrintWriter out) {
		out.println("autre élément...");
	}

	@GetMapping("/sessions/autre/clear")
	public void clear(PrintWriter out, SessionStatus status) {
		status.setComplete();
		out.println("session terminée");
	}

}
