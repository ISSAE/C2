package glg203.json.model.formes;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Forme
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Cercle.class, name = "cercle"),
    @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle")})
public abstract class Forme {
    protected Forme() {}
    
}