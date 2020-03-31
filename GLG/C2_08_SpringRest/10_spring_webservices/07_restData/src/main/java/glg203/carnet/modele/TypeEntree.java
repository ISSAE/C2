package glg203.carnet.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TypeEntree
 */
@Entity
public class TypeEntree {
    @Id
    @GeneratedValue
    Long id;

    private String label;


    public TypeEntree() {
    }

    public TypeEntree(Long id, String label) {
        this.id = id;
        this.label = label;
    }


    public TypeEntree(String label) {
        this.label = label;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TypeEntree id(Long id) {
        this.id = id;
        return this;
    }

    public TypeEntree label(String label) {
        this.label = label;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", label='" + getLabel() + "'" +
            "}";
    }


    
}