package glg203.client;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import fr.cnam.glg203.publications.CreerPublicationRequest;
import fr.cnam.glg203.publications.ListePublicationsRequest;
import fr.cnam.glg203.publications.ListePublicationsResponse;
import fr.cnam.glg203.publications.Publication;

/**
 * PublicationsClient.
 * 
 * Classe simplifiant l'usage de nos web services.
 */
public class PublicationsClient extends WebServiceGatewaySupport {

    public ListePublicationsResponse listePublications() {
        ListePublicationsRequest req = new ListePublicationsRequest();
        ListePublicationsResponse resp = (ListePublicationsResponse)(getWebServiceTemplate().
            marshalSendAndReceive("http://localhost:8080/ws", req, 
            new SoapActionCallback(""))); 
        // SoapActionCallback : header SOAPAction: used to indicate the intent of the SOAP HTTP request. Obligatoire.
        return resp;
    }

    public void creerPublication(String titre, String contenu) {
        CreerPublicationRequest req= new CreerPublicationRequest();
        Publication publication = new Publication();
        publication.setTitre(titre);
        publication.setContenu(contenu);
        req.setMaPublication(publication);
        getWebServiceTemplate().
            marshalSendAndReceive("http://localhost:8080/ws", req, 
            new SoapActionCallback("")); 
    }

}