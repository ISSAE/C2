package glg203.json.apps;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import glg203.json.model.divers.*;

/**
 * SansAccesseurs.
 * 
 * Version pas vraiment conseillée, mais montrant l'usage de @JSonProperty pour permettre
 * l'accès direct à un champ (pas public par ailleurs.)
 */

public class SansAccesseurs {
    public static void main(String[] args) throws IOException{
        ObjectMapper mapper= new ObjectMapper();
        ClasseSansAccesseur a= mapper.readValue("{\"champ1\": \"valeur1\", \"champ2\": \"valeur2\" }", ClasseSansAccesseur.class);
        System.out.println(a);
    }
}