package glg203.json.apps;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import glg203.json.model.divers.*;

/**
 * Sauvegarde d'un objet Java en JSon.
 */
public class DemoJava2JSon {

    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper= new ObjectMapper();
        Personne p= new Personne("Alan", "Turing", 30);
        StringWriter w= new StringWriter();
        mapper.writeValue(w, p);
        System.out.println(w.toString());
    }
}