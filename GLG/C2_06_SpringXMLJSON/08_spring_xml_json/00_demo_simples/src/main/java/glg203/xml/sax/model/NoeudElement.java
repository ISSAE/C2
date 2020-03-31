package glg203.xml.sax.model;

import java.util.ArrayList;
import java.util.List;


public class NoeudElement extends Noeud {
    private String label;
    private List<Noeud> fils= new ArrayList<>();

    public NoeudElement(String label) {
        this.label = label;
    }
    
    public void addFils(Noeud n) {
        fils.add(n);
    }

    public List<Noeud> getFils() {
        return fils;
    }

    public String getLabel() {
        return label;
    }
    
    

    @Override
    public <T> T accept(NoeudVisiteur<T> v) {
       return v.visitElement(this);
    }

}
