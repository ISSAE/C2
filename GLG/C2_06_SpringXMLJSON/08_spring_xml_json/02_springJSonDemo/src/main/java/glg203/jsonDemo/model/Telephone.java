package glg203.jsonDemo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Telephone
 */
public class Telephone {
    private TypeInfo typeInfo;
    private String numero;

    @JsonCreator
    public Telephone(
        @JsonProperty("typeInfo") TypeInfo typeInfo, @JsonProperty("numero") String numero) {
        this.typeInfo = typeInfo;
        this.numero = numero;
    }


    public TypeInfo getTypeInfo() {
        return this.typeInfo;
    }

    public String getNumero() {
        return this.numero;
    }

    @Override
    public String toString() {
        return typeInfo + " " + numero;
    }
}