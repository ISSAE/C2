package glg203.json.apps;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import glg203.json.model.formes.*;

/**
 * Démonstration de la lecture et de l'écriture d'objets Java en JSON, avec Héritage.
 * 
 * <p> Les objets héritent tous de la classe "Forme" et sont rangés dans un tableau.
 */
public class DemoJSonAvecHeritage {

    /**
     * On va utiliser cette classe pour éviter les problèmes 
     * liés aux types génériques.
     */
    private static class FormeList extends ArrayList<Forme>{}

    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        System.out.println();
        ObjectMapper mapper= new ObjectMapper();
        // On crée les objets
        Cercle c= new Cercle(2,4,6);
        Rectangle r= new Rectangle(4,7,10,11);
        FormeList formes= new FormeList();
        formes.add(c);
        formes.add(r);
        // On les écrit dans une String qu'on affiche
        String s= mapper.writeValueAsString(formes);
        System.out.println(s);
        // On les relit. Pour éviter les problèmes
        // liés à l'effacement des types génériques, on 
        // a créé la classe FormeList - qui "sait" qu'elle est une liste de Formes,
        // ce qu'ignorerait après compilation une ArrayList<Forme>.
        FormeList f= mapper.readValue(new StringReader(s), FormeList.class);
        System.out.println(f);        
    }
}