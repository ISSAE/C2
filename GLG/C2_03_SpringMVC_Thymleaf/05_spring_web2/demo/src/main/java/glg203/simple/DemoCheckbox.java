package glg203.simple;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/simple/checkbox")
public class DemoCheckbox {

	@GetMapping()
	public String get() {
		return "checkbox";
	}

	/**
	 * Initialise la propriété "choix" de l'objet choixCheckBox avec les paramètres de nom "choix".
	 * @param choixCheckBox
	 * @param model
	 * @return
	 */
	@PostMapping()
	public String post(ChoixCheckBox choixCheckBox, Model model) {
		model.addAttribute("selection", choixCheckBox.getChoix());
		return "listeChoix";
	}


	// Version alternative...
	//
	//@PostMapping()
	//public String post(String[] choix, Model model) {
	//	model.addAttribute("selection", choix);
	//	return "listeChoix";
	//}

}
