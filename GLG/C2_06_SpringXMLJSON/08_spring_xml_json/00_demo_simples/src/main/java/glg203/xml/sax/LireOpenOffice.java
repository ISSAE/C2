package glg203.xml.sax;

import javax.xml.parsers.*;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import java.io.InputStream;
import java.io.StringReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Démonstration de lecture d'un fichier au format sxw (un vieux format
 * d'OpenOffice) et de son affichage.
 * 
 * <p>
 * Le format sxw (comme le format actuel) est un zip qui contient le texte sous
 * forme d'un fichier xml, plus des ressources (par exemple les images incluses
 * dans le document).
 * 
 * <p>
 * On détecte et affiche "correctement" les notes de bas de page.
 * 
 * <p>
 * Le code du main n'a pas grand chose à voir avec ce cours-ci : il s'agit
 * essentiellement de chercher le fichier "content.xml" dans le fichier zip.
 * 
 * <p>
 * C'est un peu compliqué par le fait que la classe ZipFile, qui est plus
 * simple, ne fonctionne qu'avec des Files - et donc pas avec des ressources
 * java embarquées dans un jar.
 */
public class LireOpenOffice extends org.xml.sax.helpers.DefaultHandler {

	public static void main(final String[] args) throws Exception {
		final InputStream inputStream = LireOpenOffice.class.getResourceAsStream("/glg203/ressources/xml/demo.sxw");
		try (ZipInputStream zip = new ZipInputStream(inputStream);) {
			boolean contentXmlNotFound = true;
			ZipEntry entry;
			// On avance dans le fichier zip jusqu'à ce qu'on trouve "content.xml"
			while (contentXmlNotFound && (entry = zip.getNextEntry()) != null) {
				if (entry.getName().equals("content.xml")) {
					contentXmlNotFound = false;
				}
			}
			if (!contentXmlNotFound) {
				final LireOpenOffice handler = new LireOpenOffice();
				final SAXParserFactory factory = SAXParserFactory.newInstance();
				factory.setFeature("http://xml.org/sax/features/namespaces", true);
				factory.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
				factory.setValidating(false);
				final SAXParser parser = factory.newSAXParser();
				// On est à la bonne position, et "zip" va se conduire comme un InputStream
				// normal.
				final InputSource src = new InputSource(zip);
				src.setSystemId("file:///");
				parser.parse(src, handler);
			}
		}
	}

	String currentFootnote;

	int footnoteNumber;
	Map<Integer, String> footnotes;
	boolean inFootnote;

	public LireOpenOffice() {
		inFootnote = false;
		footnoteNumber = 0;
		footnotes = new TreeMap<>();
		currentFootnote = null;
	}

	/**
	 * 
	 */
	private void beginFootnote() {
		currentFootnote = "";
		inFootnote = true;

	}

	@Override
	public void characters(final char[] charArray, final int start, final int length) throws SAXException {
		final String s = new String(charArray, start, length);
		ecrire(s);
	}

	/**
	 * @param string
	 */
	private void ecrire(final String string) {
		if (inFootnote)
			currentFootnote = currentFootnote + string;
		else
			System.out.print(string);
	}

	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException {
		if (qName.equals("text:footnote"))
			endFootNote();
		else if (qName.equals("text:p"))
			ecrire("\n\n");
		else if (qName.equals("office:document-content"))
			finText();
	}

	/**
	 * 
	 */
	private void endFootNote() {
		inFootnote = false;
		footnoteNumber++;
		footnotes.put(footnoteNumber, currentFootnote);
		currentFootnote = null;
		ecrire("[[" + footnoteNumber + "]]");
	}

	private void finText() {
		for (Entry<Integer, String> e : footnotes.entrySet()) {
			ecrire("\n[[" + e.getKey() + "]] : " + e.getValue());
		}
	}

	@Override
	public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException {
		if (systemId.equals("file:///office.dtd")) {
			return new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
		} else
			return null;
	}

	@Override
	public void startElement(final String uri, final String localName, final String qname, final Attributes attributes)
			throws SAXException {
		// ecrire("<" + qname + ">");
		switch (qname) {
		case "text:footnote":
			beginFootnote();
			break;
		case "text:tab-stop":
			ecrire("\t");
			break;
		case "text:line-break":
			ecrire("\n");
			break;
		}
	}

} // TestXML
