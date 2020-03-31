package glg203.jsonDemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import glg203.jsonDemo.model.Personne;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Writer;
import java.awt.Color;
import java.awt.Graphics;

/**
 * ConverterDemoController
 */
@Controller
public class ConverterDemoController {

    /**
     * L'intérêt (relatif) de cette méthode par rapport à renvoyer un flux binaire 
     * est que le système est capable de négocier un type de média (et donc d'envoyer au choix du png ou du jpeg)
     * @return
     */
    @GetMapping(value="/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public BufferedImage getImage() {
        BufferedImage img= new BufferedImage(800,800, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.createGraphics();
        g.setColor(Color.GREEN);
        g.drawLine(0, 0, 800, 800);
        g.dispose();
        return img;
    }
    
    @GetMapping("/personne")
    @ResponseBody
    public Personne getPersonne() {
        return new Personne("Ada", "Lovelace");
    }

    @PostMapping("/personne")
    public void setPersonne(@RequestBody Personne p, Writer out) throws IOException {
        out.write("Personne : "+p.getNom() + " "+ p.getPrenom() + "\n");
    }
}