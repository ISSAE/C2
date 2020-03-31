package glg203.jsonDemo.config;


import com.fasterxml.jackson.databind.DeserializationFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * JSonEtXmlConfiguration.
 * 
 * Ici, on va paramétrer Jackson pour affiner ses résultats.
 * 
 * Noter qu'il est aussi possible de faire des paramétrages simples dans application.properties.
 */
@Configuration
public class JSonEtXmlConfiguration {

    @Bean
    @Order(1)
    /**
     * Crée un builder d'object mapper, qui sera utilisé après (order=1)
     * celui fourni par défaut et qui en modifiera les caractéristiques.
     * 
     * <p> Ce système permet de moduler les modifications faites au builder par défaut.
     * @return
     */
    public Jackson2ObjectMapperBuilder prettyPrintMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true);
        builder.featuresToDisable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        return builder;
    }
    
}