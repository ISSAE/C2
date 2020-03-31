package glg203.xml.jaxb;

import glg203.ressources.jaxb.*;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Démonstration de l'utilisation de Jaxb.
 * <p>Les classes de glg203.ressources.jaxb.* sont créées à partir
 * du fichier person.xsd.
 */
public class DemoJaxbAvecGeneration {

    public static void main(String[] args) throws JAXBException, IOException {
        Personne p = new Personne();
        p.setId(BigInteger.valueOf(3));
        p.setNom("toto");
        p.setTelephones(new Personne.Telephones());
        Telephone tel = new Telephone();
        tel.setNumero("010203040506");
        p.getTelephones().getTelephone().add(tel);
        Telephone tel1 = new Telephone();
        tel1.setNumero("0203040506");
        p.getTelephones().getTelephone().add(tel1);
        JAXBContext jaxbc = JAXBContext.newInstance(Personne.class);
        Marshaller m = jaxbc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter w = new StringWriter();
        m.marshal(p, w);
        w.close();
        System.out.println("***Résultat\n*************\n" + w.toString()+"\n*********");
    }

}
