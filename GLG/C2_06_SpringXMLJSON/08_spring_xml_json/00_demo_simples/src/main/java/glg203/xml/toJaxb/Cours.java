package glg203.xml.toJaxb;

import glg203.ressources.jaxb.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author rosmord
 */
public class Cours {

    private String titre = "";
    private List<String> labels = new ArrayList<String>();

    public Cours() {
    }

    public Cours(String titre) {
        this.titre= titre;
    }
    
    public void addLabel(String l) {
        labels.add(l);
    }

    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Cours{" + "titre=" + titre + ", labels=" + labels + '}';
    }


    
    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbc = JAXBContext.newInstance(Cours.class);
        Cours c= new Cours("glg203");
        c.addLabel("java");
        c.addLabel("pattern");
        StringWriter w= new StringWriter();
        JAXB.marshal(c, w);
        String s= w.toString();
        System.out.println(s);
        
        
        
        Cours c1 = JAXB.unmarshal(new StringReader(s), Cours.class);
        System.out.println(c1);
    }

}
