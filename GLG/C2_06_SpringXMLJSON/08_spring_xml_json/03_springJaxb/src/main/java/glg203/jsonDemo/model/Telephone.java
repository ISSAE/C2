package glg203.jsonDemo.model;

/**
 * Telephone
 */
public class Telephone {
    private TypeInfo typeInfo;
    private String numero;

    public Telephone() {
        super();
    }

    public Telephone(TypeInfo typeInfo, String numero) {
        this.typeInfo = typeInfo;
        this.numero = numero;
    }


    public TypeInfo getTypeInfo() {
        return this.typeInfo;
    }

    public String getNumero() {
        return this.numero;
    }

    /**
     * @param typeInfo the typeInfo to set
     */
    public void setTypeInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }


}