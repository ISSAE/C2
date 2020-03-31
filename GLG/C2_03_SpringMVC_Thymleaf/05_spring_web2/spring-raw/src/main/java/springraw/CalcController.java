package springraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/form")
public class CalcController {

	@Autowired
	ApplicationContext context;

	@GetMapping
	public String getForm(CalcForm calcForm) {
		return "calcForm";
	}

	@PostMapping
	public ModelAndView processForm(@Valid CalcForm calcForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("calcForm");
		} else {
			return new ModelAndView("calcForm", "resultat", calcForm.sum());
		}
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello world";
	}
	
}
