package com.example.simple_springboot.ui;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.*;
import java.io.OutputStream;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DemoController
 */
@Controller
@RequestMapping("/do")
public class DemoController {

	@GetMapping("/calc")
	public void calcul(double x, HttpServletResponse response) throws IOException {
		response.getWriter().println(x * 2);
	}


	@GetMapping("/calc1")
	public void calcul1(Optional<Double> x, HttpServletResponse response) throws IOException {
		if (x.isPresent()) {
			response.getWriter().println(x.get() * 2);
		}
	}


	@GetMapping("/calcPath/{x}/plus/{y}")
	@ResponseBody
	public double calcul(@PathVariable double x, @PathVariable double y) throws IOException {
		return x + y;
	}

	@GetMapping("/hello")
	public void helloMethod(String prenom, String nom, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().println("Bonjour " + nom + " de prénom " + prenom);
	}


	@GetMapping("/hello2")
	@ResponseBody
	public String helloMethod(String prenom, String nom) throws IOException {
		return ("Bonjour " + nom + " de prénom " + prenom);
	}

	@GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getPicture(OutputStream out) throws IOException {
		BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.createGraphics();
		g.setColor(Color.RED);
		g.fillRect(0, 0, 800, 600);
		g.dispose();
		ImageIO.write(img, "jpg", out);
	}
}