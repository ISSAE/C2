package glg203.json.apps;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import glg203.json.model.divers.*;

/**
 * Classe pour montrer ce qui se passe lors de la création d'un objet par Jackson.
 */

public class AvecAccesseur {
    public static void main(String[] args) throws IOException{
        ObjectMapper mapper= new ObjectMapper();
        ClasseAvecAccesseur a= mapper.readValue("{\"champ1\": \"valeur1\", \"champ2\": \"valeur2\" }", ClasseAvecAccesseur.class);
        System.out.println(a);
    }
}