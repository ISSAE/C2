package glg203.carnet.modele;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

/**
 * ContactProjection
 */
@Projection(name = "contactFull", types = {Contact.class})
public interface ContactProjection {
    String getNom();
    String getPrenom();
    List<EntreeP> getEntrees();
    
}