package glg203.json.model.divers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ClasseSansAccesseur
 * 
 * Version pas vraiment conseillée, mais montrant l'usage de @JSonProperty pour permettre
 * l'accès direct à un champ (pas public par ailleurs.)
 */
 
public class ClasseSansAccesseur {

    @JsonProperty // Nécessaire, puisque pas d'accesseur
    String champ1;

    @JsonProperty // Voir le setter.
    String champ2;
    
    /**
     * On met quand même un accesseur ici.
     * <p>Sans le JSonIgnore, il serait utilisé.
     * <p>Avec le JSonIgnore, il faut ajouter un @JSonProperty
     * au champ pour que celui-ci ne soit pas complètement ignoré.
     */
    @JsonIgnore
    public void setChamp2(String champ2) {
        System.out.println("appel de setChamp2 avec "+champ2);
        this.champ2 = champ2;
    }

    @Override
    public String toString() {
        return champ1 + " " + champ2;        
    }
}