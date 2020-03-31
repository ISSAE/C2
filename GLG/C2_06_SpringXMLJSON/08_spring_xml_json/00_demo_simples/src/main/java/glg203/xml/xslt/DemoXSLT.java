package glg203.xml.xslt;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Démonstration de plusieurs opérations avec XSLT.
 */
public class DemoXSLT {
    private static final String XMLBASE= "/glg203/ressources/xml/";
    private static final String XSLTBASE= "/glg203/ressources/xslt/";

    public static void main(String[] args) throws IOException, SAXException, TransformerException {        
        demoProcessFile("ne recopie que le texte", "demo1.xml", "defaut.xsl");
        demoProcessFile("met en valeur le nom des auteurs",  "demo1.xml", "auteur.xsl");
        demoProcessFile("ne recopie rien", "demo1.xml", "vide.xsl");
        demoProcessFile("remplace la balise 'auteur' par 'nomAuteur'",  "demo1.xml", "mecanisme.xsl");
        demoProcessFile("passe en HTML", "demo1.xml", "personnesVersHTML.xsl");
        demoProcessFile("crée une table des matières pour un document html", "texteLong.xml",
                "tableDesMatieres.xsl");
    }
 
    /**
     * traite un fichier XML en avec un fichier XSLT.
     * 
     * @return le texte produit.
     */
    private static String processFile(InputStream xml, InputStream xslt)
            throws TransformerConfigurationException, SAXException, TransformerException {
        String result;
        // XSLT source
        TransformerFactory factory2 = TransformerFactory.newInstance();
        Transformer f = factory2.newTransformer(new StreamSource(xslt));
        // Destination
        StringWriter stringWriter = new StringWriter();
        StreamResult outputTarget = new StreamResult(stringWriter);
        // On crée le XMLReader
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        // On fixe un resolver "bidon" parce qu'on ne veut pas s'embêter avec les dtd.
        xmlReader.setEntityResolver((publicId,
                systemId) -> new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")));
        // On prépare la source
        SAXSource source1 = new SAXSource(xmlReader, new InputSource(xml));
        // Tout est prêt : on applique le template XSLT.
        f.transform(source1, outputTarget);
        // Le résultat a été écrit dans outputTarget (donc dans le stringWriter)
        result = stringWriter.toString().trim();
        return result;
    }

    public static void demoProcessFile(String message, String xmlFile, String xsltFile)
            throws IOException, SAXException, TransformerException {       
        System.out.println("On transforme " + xmlFile + " avec " + xsltFile);
        System.out.println(message);
        System.out.println();
        // try-with-ressources...
        try (InputStream xmlIn = DemoXSLT.class.getResourceAsStream(XMLBASE + xmlFile);
                InputStream xsltIn = DemoXSLT.class.getResourceAsStream(XSLTBASE+ xsltFile)) {
            if (xmlIn == null) {
                throw new RuntimeException("File not found " + xmlFile);
            }
            if (xsltFile == null) {
                throw new RuntimeException("File not found " + xsltFile);
            }
            String s = processFile(xmlIn, xsltIn);
            System.out.println(s);
        }
        System.out.println("****************************************");
    }



}
