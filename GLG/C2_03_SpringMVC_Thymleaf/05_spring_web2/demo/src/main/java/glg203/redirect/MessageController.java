package glg203.redirect;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/redirection")
public class MessageController {

	@Autowired
	MessageRepository messageRepository;

	@GetMapping("/form1")
	public String getForm1(Model model) {
		model.addAttribute("comment", "Version sans redirection.");
		return "formulaireEtListe";
	}

	@PostMapping("/form1")
	public String postForm1(String nouveauMessage, Model model) {
		// traitement d'erreur sommaire : monMessage ne doit pas être vide...
		if (Strings.isEmpty(nouveauMessage)) {
			model.addAttribute("erreur", "le nouveau message ne doit pas être vide");
		} else {
			messageRepository.add(nouveauMessage);
		}
		model.addAttribute("messages", messageRepository.getMessages());
		model.addAttribute("comment", "Version sans redirection.");
		return "formulaireEtListe";
	}


	/**
	 * Même traitement que getList1, mais on va utiliser des redirections pour afficher le résultat.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/form2")
	public String getForm2(Model model) {
		model.addAttribute("comment", "Version avec redirection.");
		return "formulaireEtListe";
	}

	/**
	 * Même traitement que getList1, mais on va utiliser des redirections pour afficher le résultat.
	 *
	 * @param nouveauMessage
	 * @param model
	 * @return
	 */
	@PostMapping("/form2")
	public String postForm2(String nouveauMessage, Model model) {
		// traitement d'erreur sommaire : monMessage ne doit pas être vide...
		if (Strings.isEmpty(nouveauMessage)) {
			model.addAttribute("erreur", "le nouveau message ne doit pas être vide");
			model.addAttribute("messages", messageRepository.getMessages());
			model.addAttribute("comment", "Version avec redirection.");
			return "formulaireEtListe";

		} else {
			messageRepository.add(nouveauMessage);
			return "redirect:/redirection/list2";
		}
	}

	@GetMapping("/list2")
	public String getlist2(Model model) {
		model.addAttribute("messages", messageRepository.getMessages());
		model.addAttribute("comment", "Version avec redirection.");
		return "liste";
	}

	/**
	 * Version du précédent où on retourn ModelAndView...
	 *
	 * @return
	 */
	@GetMapping("/list2Bis")
	public ModelAndView getlist2Bis() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("messages", messageRepository.getMessages());
		modelAndView.addObject("comment", "Version avec redirection.");
		modelAndView.setViewName("liste");
		return modelAndView;
	}


	/**
	 * Version plus ergonomique des codes précédents.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/form3")
	public String getForm3(Model model) {
		model.addAttribute("comment", "Version avec redirection, mais plus sympathique...");
		model.addAttribute("messages", messageRepository.getMessages());
		return "formulaireEtListe";
	}

	@PostMapping("/form3")
	public String postForm3(String nouveauMessage, Model model) {
		// traitement d'erreur sommaire : monMessage ne doit pas être vide...
		if (Strings.isEmpty(nouveauMessage)) {
			model.addAttribute("erreur", "le nouveau message ne doit pas être vide");
			model.addAttribute("messages", messageRepository.getMessages());
			model.addAttribute("comment", "Version avec redirection.");
			return "formulaireEtListe";
		} else {
			messageRepository.add(nouveauMessage);
			// On redirige vers nôtre URL... mais en mode GET.
			return "redirect:/redirection/form3";
		}
	}

	@GetMapping("/message/{num}")
	public String montreMessage(@PathVariable int num, Model model) {
		Message message= messageRepository.getMessage(num);
		model.addAttribute("message", message);
		return "affichageMessage";
	}

}
