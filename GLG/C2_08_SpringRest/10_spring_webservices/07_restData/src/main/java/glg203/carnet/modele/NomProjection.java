package glg203.carnet.modele;

import org.springframework.data.rest.core.config.Projection;

import glg203.carnet.modele.Contact;

/**
 * Une projection. Note : elles <strong>doivent</strong> être au même 
 * endroit que les entités, ou être déclarées par programme.
 */
@Projection(name = "nomP", types = {Contact.class})
public interface NomProjection {

    String getNom();
    String getPrenom();

}