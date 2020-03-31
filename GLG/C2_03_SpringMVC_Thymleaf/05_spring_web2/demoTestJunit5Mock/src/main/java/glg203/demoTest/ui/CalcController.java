package glg203.demoTest.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import glg203.demoTest.service.CalcService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalcController {

	ApplicationContext context;

	CalcService calcService;


	public CalcController(ApplicationContext context, CalcService calcService) {
		this.context = context;
		this.calcService = calcService;
	}
	

	@GetMapping("/")
	public String getForm(@Valid CalcForm calcForm) {
		return "calcForm";
	}

	@PostMapping("/")
	public String processForm(@Valid CalcForm calcForm, BindingResult bindingResult, Model model) {
		if ( ! bindingResult.hasErrors()) {			
			model.addAttribute("resultat", calcForm.sum());
		} else {		
			// model.addAttribute("erreurs", buildErrorMessages(bindingResult));
		}
		return "calcForm";
	}

	/**
	 * Une méthode pas très adroite pour récupérer le texte des messages d'erreur.
	 * En réalité, les framework web liés à Spring (Thymeleaf, Bibliothèque Spring pour JSP)
	 * disposent d'outils qui font ce travail à notre place...
	 * @param bindingResult
	 * @return
	 */
	private List<String> buildErrorMessages(BindingResult bindingResult) {
		List<String> messages= new ArrayList<>();
		for (ObjectError error : bindingResult.getAllErrors()) {
			String message =  context.getMessage(error, null);
			if (error instanceof FieldError) {
				FieldError fieldError= (FieldError)error;
				message= fieldError.getField() + ": " + message;
			}
			messages.add(message);
		}
		return messages;

	}
}
