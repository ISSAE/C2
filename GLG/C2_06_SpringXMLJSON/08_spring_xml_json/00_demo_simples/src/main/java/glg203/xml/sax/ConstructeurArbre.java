package glg203.xml.sax;

import glg203.xml.sax.model.AfficheurArbre;
import glg203.xml.sax.model.Noeud;
import glg203.xml.sax.model.NoeudElement;
import glg203.xml.sax.model.NoeudTexte;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Builder qui sait construire un arbre à partir d'un fichier xml en utilisant SAX.
 * <p> Complete waste of time : il vaut évidemment mieux utiliser JDom ou Dom qui font déjà le 
 * travail pour vous...
 * @author rosmord
 */
public class ConstructeurArbre {

    private MyHandler handler = null;

    public Noeud lire(InputStream in) throws ParserConfigurationException, SAXException, IOException {
        handler = new MyHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // On désactive la validation (des fois qu'on n'aie pas de DTD disponible)
        factory.setValidating(true);
        SAXParser parser = factory.newSAXParser();
        InputSource src = new InputSource(in);
        //src.setSystemId("file:///");
        parser.parse(src, handler);
        return handler.getResult();
    }

    private class MyHandler extends DefaultHandler {

        private Stack<NoeudElement> pile = new Stack<>();
        private NoeudElement result = null;

        private Noeud getResult() {
            return result;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String s = new String(ch, start, length);
            NoeudElement parent = pile.peek();
            parent.addFils(new NoeudTexte(s));
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            // On dépile, car on est sorti de l'élément...
            pile.pop();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            NoeudElement n = new NoeudElement(qName);
            if (pile.empty()) {
                result = n;
            } else {
                NoeudElement parent = pile.peek();
                parent.addFils(n);
            }
            pile.push(n);
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public InputSource resolveEntity(String publicId, String systemId)
                throws SAXException {
                return new InputSource(
                        new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
           
        }
    }

    public static void main(String[] args) throws Exception {
        //InputStream in = ConstructeurArbre.class.getResourceAsStream("/glg203/xml/documents/texte.xml");
        InputStream in = ConstructeurArbre.class.getResourceAsStream("/glg203/xml/documents/pom1.xml");
        Noeud n = new ConstructeurArbre().lire(in);
        new AfficheurArbre().afficher(n);
    }
}
