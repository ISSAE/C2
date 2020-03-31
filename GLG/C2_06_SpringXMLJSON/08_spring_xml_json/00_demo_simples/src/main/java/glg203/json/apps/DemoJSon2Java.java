package glg203.json.apps;

import java.io.IOException;
import java.io.StringReader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import glg203.json.model.divers.*;
 
/**
 * Lecture d'un objet sauvé en JSON et création d'un objet Java
 */
public class DemoJSon2Java {

    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        String data= "{\"nom\":\"Alan\",\"prenom\":\"Turing\",\"age\":30}";
        ObjectMapper mapper= new ObjectMapper();
        StringReader src= new StringReader(data);
        Personne2 p= mapper.readValue(src, Personne2.class);        
        System.out.println("ici "+ p.getNom()+ " age: " + p.getAge());
    }
}