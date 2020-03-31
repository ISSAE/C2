package glg203.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/simple")
public class ControleurSimple {

	/**
	 * Exemple très simple de contrôle avec deux paramètres, a et b.
	 *
	 * @param a
	 * @param b
	 * @return un modèle et une vue...
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView somme(int a, int b) {
		int somme = a + b;
		return new ModelAndView("simpleCalc", "resultat", somme);
	}

	/**
	 * Le même exemple, mais avec un objet pour représenter les paramètres.
	 *
	 * @param deux
	 * @param un
	 * @return ...
	 */
	@RequestMapping(value = "/add1", method = RequestMethod.GET)
	public ModelAndView somme(SommeSimple forme) {
		int somme = forme.getA() + forme.getB();
		return new ModelAndView("simpleCalc", "resultat", somme);
	}

	/**
	 * Exemple peu réaliste, pour montrer ce qui se passe quand on a deux objets avec des propriétés communes.
	 *
	 * @param deux
	 * @param un
	 * @return ...
	 */
	@RequestMapping(value = "/add2", method = RequestMethod.GET)
	public ModelAndView somme(Deux deux, Un un) {
		int somme = un.getA() + deux.getB();
		System.err.println("Un vaut " + un);
		return new ModelAndView("simpleCalc", "resultat", somme);
	}

	/**
	 * Juste pour montrer qu'on peut avoir plusieurs méthodes...
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	@RequestMapping(value = "/div", method = RequestMethod.GET)
	public ModelAndView division(int a, int b) {
		int somme = a / b;
		return new ModelAndView("simpleCalc", "resultat", somme);
	}

	/**
	 * Cette fois-ci, les données sont dans le chemin.
	 */
	@GetMapping("/add/{a}/plus/{b}")
	public ModelAndView somme2(@PathVariable("a") int a, @PathVariable("b") int b) {
		int somme = a + b;
		return new ModelAndView("simpleCalc", "resultat", somme);
	}

	/**
	 * Exemple d'affichage direct, sans passer par une vue.
	 *
	 * @param out      : sortie pour affichage
	 * @param response : la réponse (on en a besoin pour le type du contenu).
	 */
	@GetMapping(value = "/cercle")
	public void cercle(OutputStream out, HttpServletResponse response) throws IOException {
		// note: l'attribut "produces" de RequestMapping est seulement utile quand il y a
		// négociation de contenu.
		response.setContentType("image/png");
		int taille = 500;
		int marge = 10;
		int diametre = taille - 2 * marge;
		BufferedImage img = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.RED);
		g.fillOval(marge, marge, diametre, diametre);
		g.dispose();
		ImageIO.write(img, "png", out);
	}

	@GetMapping(value = "/cercle1", produces = {"image/png"})
	@ResponseBody
	public byte[] cercle() throws IOException {
		int taille = 500;
		int marge = 10;
		int diametre = taille - 2 * marge;
		BufferedImage img = new BufferedImage(taille, taille, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.RED);
		g.fillOval(marge, marge, diametre, diametre);
		g.dispose();
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);
		return out.toByteArray();
	}
}
