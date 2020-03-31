package glg203.json.apps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import glg203.json.model.jsonView.FullView;
import glg203.json.model.jsonView.MotDePasseView;
import glg203.json.model.jsonView.NomEtIdView;
import glg203.json.model.jsonView.Utilisateur;

/**
 * DemoJSonView
 */
public class DemoJSonView {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper= new ObjectMapper();
        Utilisateur u = new Utilisateur(3l, "unnom", "un mot de passe");
        System.out.println("Complet : "+ mapper.writeValueAsString(u));
        System.out.print("Vue sans MdP : ");
        System.out.println(
            mapper.writerWithView(NomEtIdView.class)
                .writeValueAsString(u)
        );
        System.out.print("Vue MdP seul : ");
        
        System.out.println(
            mapper.writerWithView(MotDePasseView.class)
                .writeValueAsString(u)
        );
        System.out.print("Vue complète (mais sans vue du tout, ça marche aussi) : ");
        System.out.println(
            mapper.writerWithView(FullView.class)
                .writeValueAsString(u)
        );
    }
}