package glg203.xml.jdom;

import java.io.IOException;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
 *
 * @author rosmord
 */
public class DomDump {

    public static void main(String[] args) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setFeature(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                false);
        InputSource xmlSource = new InputSource(DomDump.class.getResourceAsStream(
            "/glg203/ressources/xml/texte.xml"));
        Document jdomDocument = builder.build(xmlSource);
        affiche(jdomDocument.getRootElement(), 0);
    }

    private static void affiche(Content c, int marge) {
        switch (c.getCType()) {
            case Element:
                Element e = (Element) c;
                decale(marge);
                System.out.println("élément " + e.getQualifiedName());
                decale(marge);
                System.out.println("Contenu ");
                for (Content fils : e.getContent()) {
                    affiche(fils, marge + 1);
                }
                break;
            case Text:
                Text t = (Text) c;
                decale(marge);
                System.out.println(t.getTextTrim());
                break;
        }
    }

    private static void decale(int marge) {
        for (int i = 0; i < marge; i++) {
            System.out.print(" ");
        }
    }

}
