package glg203.webservice;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.cnam.glg203.publications.*;

/**
 * Méthodes publiées par le service web.
 * <p>
 * Une fois celui-ci en place :
 * <ul>
 * <li>On accède au fichier wsdl par l'URL
 * http://localhost:8080/ws/publications.wsdl
 * <li>
 * </ul>
 * 
 * @author rosmord
 */
@Endpoint
public class PublicationFacade {

	@Autowired
	PublicationService service;
	
	

	@PayloadRoot(namespace = Constantes.NAMESPACE_URI, localPart = "listePublicationsRequest")	
	public @ResponsePayload ListePublicationsResponse listePublications() {
		Publications publications = new Publications();
		publications.getPublications().addAll(service.getPublications());
		ListePublicationsResponse response = new ListePublicationsResponse();
		response.setListe(publications);
		return response;
	}

	@PayloadRoot(namespace = Constantes.NAMESPACE_URI, localPart = "creerPublicationRequest")
	public  void ajouterPublication(@RequestPayload CreerPublicationRequest creerPublicationRequest) {
		service.add(creerPublicationRequest.getMaPublication());		
	}

}
