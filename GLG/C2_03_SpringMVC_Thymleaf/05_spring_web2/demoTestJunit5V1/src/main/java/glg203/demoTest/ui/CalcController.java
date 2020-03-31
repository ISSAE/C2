package glg203.demoTest.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CalcController {

	@Autowired
	ApplicationContext context;

	@GetMapping("/")
	public String getForm(@Valid CalcForm calcForm) {
		return "calcForm";
	}

	@PostMapping("/")
	public ModelAndView processForm(@Valid CalcForm calcForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("calcForm", "erreurs", buildErrorMessages(bindingResult));
		} else {
			return new ModelAndView("calcForm", "resultat", calcForm.sum());
		}
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
