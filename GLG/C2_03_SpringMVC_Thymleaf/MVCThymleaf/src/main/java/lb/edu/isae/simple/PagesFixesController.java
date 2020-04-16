package lb.edu.isae.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
/**
 * Un contrôleur pour les pages fixes.
 * A priori, pas optimal du tout. On fera mieux ensuite...
 */
public class PagesFixesController {

	@GetMapping("/**/{page}.htm")
	public String page(@PathVariable("page") String page) {
		return "fixes/"+ page;
	}

	
}
