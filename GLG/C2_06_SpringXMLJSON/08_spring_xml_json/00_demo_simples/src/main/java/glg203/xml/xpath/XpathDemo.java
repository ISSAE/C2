package glg203.xml.xpath;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Démonstrations diverses d'utilisations de XPATH.
 */
public class XpathDemo {

    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        String pomString = "/glg203/ressources/xml/pom1.xml";
        String demo1 = "/glg203/ressources/xml/demo1.xml";
        doXPath(demo1, "descendant::*");
        doXPath(demo1, "descendant::revue/descendant::*");

        doXPath(demo1, "descendant::auteur");
        doXPath(demo1, "descendant::article/text()");
        doXPath(demo1, "descendant::article[3]/attribute::pages");

        doXPath(demo1, "descendant::text()");
        doXPath(demo1, "descendant::auteur/text()");
        doXPath(demo1, "descendant::auteur/ancestor::article");
        doXPath(demo1, "descendant::auteur/ancestor::article/attribute::pages");
        doXPath(demo1, "descendant::auteur[contains(.,'Lovelace')]/text()");
        doXPath(demo1, "descendant::article[contains(.,'Lovelace')]/attribute::pages");

        doXPath(demo1, "descendant[attribute/text()]");
        doXPath(demo1, "//*[1]");

        doXPath(demo1, "//article/@pages");
        doXPath(demo1, "//article[count(*) > 2]/@pages");
        // éléments avec un attribut pages
        doXPath(demo1, "//*[@pages]");
        // éléments avec un attribut pages égal à 61-80
        doXPath(demo1, "//*[@pages='61-80']");
        // éléments avec un attribut pages non égal à 61-80
        doXPath(demo1, "//*[not(@pages='61-80')]");

        // articles avec un auteur
        doXPath(demo1, "//article[auteur]/@pages");
        
        // la version d'un pom.
        doXPath(pomString, "//pom:modelVersion/text()");
    }

    /**
     * Affiche les Résultats d'une recherche XPath dans un document.
     * @param docPath le chemin du document (ressource de ce projet)
     * @param search la requête XPath.
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    public static void doXPath(String docPath, String search) throws XPathExpressionException, SAXException, ParserConfigurationException, IOException {
        System.out.println("******************************************************");
        System.out.println("* Recherche " + search);
        System.out.println("*");
        InputStream docIn = XpathDemo.class.getResourceAsStream(docPath);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        org.w3c.dom.Document doc = builder.parse(docIn);

        if (docIn == null) {
            throw new NullPointerException();
        }
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        NamespaceContext n = new MyNsContext();
        xpath.setNamespaceContext(n);
        XPathExpression v = xpath.compile(search);
        InputSource source = new InputSource(docIn);

        NodeList list = (NodeList) v.evaluate(source, XPathConstants.NODESET);
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            System.out.println("Résutat " + i + " : " + item);
        }
        System.out.println();
        System.out.println();
    }

    private static class MyNsContext implements NamespaceContext {

        private HashMap<String, String> prefixToUri = new HashMap<>();
        private HashMap<String, String> uriToPrefix = new HashMap<>();

        private final void add(String prefix, String uri) {
            prefixToUri.put(prefix, uri);
            uriToPrefix.put(uri, prefix);
        }

        public MyNsContext() {
            add("pom", "http://maven.apache.org/POM/4.0.0");
        }

        @Override
        public String getNamespaceURI(String prefix) {
            return prefixToUri.get(prefix);
        }

        @Override
        public String getPrefix(String namespaceURI) {
            return uriToPrefix.get(namespaceURI);
        }

        @Override
        public Iterator getPrefixes(String namespaceURI) {
            return prefixToUri.keySet().iterator();
        }
    }


}
