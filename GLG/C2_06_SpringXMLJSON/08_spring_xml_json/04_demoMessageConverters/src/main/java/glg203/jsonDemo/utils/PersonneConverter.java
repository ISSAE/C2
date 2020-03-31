package glg203.jsonDemo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import glg203.jsonDemo.model.Personne;

/**
 * PersonneConverter.
 * 
 * <p>
 * Un convertisseur permettant de passer d'un objet de classe Personne à du HTML
 * et de texte "brut" à un objet personne.
 * 
 * <p>
 * Pour la lecture, le texte expédié doit avoir la forme "prenom:nom"
 * <p>
 * On pourrait aussi étendre AbstractHttpMessageConverter, mais dans notre cas,
 * ça serait moins précis pour les types traités.
 * 
 * <p>
 * Note : ce code est faux : il faudrait protéger les caractères spéciaux en
 * sortie, et savoir les lire en entrée.
 * 
 * On peut éventuellement écrire des convertisseurs pour lesquels seule une des
 * méthodes est vraiment implémentée.
 */
public class PersonneConverter implements HttpMessageConverter<Personne> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return clazz.equals(Personne.class)
                && (mediaType == null || mediaType.equalsTypeAndSubtype(MediaType.TEXT_PLAIN));
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return Personne.class.equals(clazz)
                && (mediaType == null || mediaType.equalsTypeAndSubtype(MediaType.TEXT_HTML));
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.TEXT_HTML, MediaType.TEXT_PLAIN);
    }

    @Override
    public Personne read(Class<? extends Personne> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        MediaType contentType = inputMessage.getHeaders().getContentType();
        if (contentType.equalsTypeAndSubtype(MediaType.TEXT_PLAIN)) {            
            try (Reader r = new BufferedReader(new InputStreamReader(inputMessage.getBody(), contentType.getCharset()))) {
                StringBuilder builder = new StringBuilder();
                int c;
                while ((c = r.read()) != -1) {
                    builder.append((char) c);
                }
                String t[] = builder.toString().split(":");
                if (t.length == 2) {
                    return new Personne(t[0], t[1]);
                } else {
                    throw new HttpMessageNotReadableException("J'attends la forme prenom:nom", inputMessage);
                }
            }
        } else {
            throw new HttpMessageNotReadableException(
                    "Je ne peux pas lire " + inputMessage.getHeaders().getContentType(), inputMessage);
        }
    }

    @Override
    public void write(Personne p, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        if (contentType.equals(MediaType.TEXT_HTML)) {
            outputMessage.getHeaders().setContentType(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));

            try (Writer w = new OutputStreamWriter(outputMessage.getBody(), "UTF-8")) {
                w.write("<html><body><h1>");
                w.write(p.getNom());
                w.write("</h1>");
                w.write(p.getPrenom());
                w.write("</body></html>");
                w.write("\n");
            }
        } else {
            throw new HttpMessageNotWritableException(
                    "Je ne peux pas fournir " + outputMessage.getHeaders().getAccept());
        }

    }

}