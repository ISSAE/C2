package glg203.jpa.model.embed;

import java.util.List;

import javax.persistence.*;

/**
 * TableDesMatieres
 */
@Entity
public class TableDesMatieres {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titreOuvrage;
    
    @ElementCollection
    @OrderColumn
    private List<String> entrees;


    public TableDesMatieres() {
    }

    public TableDesMatieres(Long id, String titreOuvrage, List<String> entrees) {
        this.id = id;
        this.titreOuvrage = titreOuvrage;
        this.entrees = entrees;
    }

    public TableDesMatieres(String titreOuvrage, List<String> entrees) {
        this.titreOuvrage = titreOuvrage;
        this.entrees = entrees;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitreOuvrage() {
        return this.titreOuvrage;
    }

    public void setTitreOuvrage(String titreOuvrage) {
        this.titreOuvrage = titreOuvrage;
    }

    public List<String> getEntrees() {
        return this.entrees;
    }

    public void setEntrees(List<String> entrees) {
        this.entrees = entrees;
    }

    public TableDesMatieres id(Long id) {
        this.id = id;
        return this;
    }

    public TableDesMatieres titreOuvrage(String titreOuvrage) {
        this.titreOuvrage = titreOuvrage;
        return this;
    }

    public TableDesMatieres entrees(List<String> entrees) {
        this.entrees = entrees;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titreOuvrage='" + getTitreOuvrage() + "'" +
            ", entrees='" + getEntrees() + "'" +
            "}";
    }

    
}