package glg203.webservice;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import fr.cnam.glg203.publications.Publication;

@Service
public class PublicationService {
	private ConcurrentLinkedQueue<Publication> publications = new ConcurrentLinkedQueue<>();


	@PostConstruct
	public void init() {
		Publication publication = new Publication();
		publication.setTitre("Un message");
		publication.setContenu("bienvenue");
		publications.add(publication);
	}
	
	public void add(Publication publication) {
		publications.add(publication);
	}

	public ConcurrentLinkedQueue<Publication> getPublications() {
		return publications;
	}
}
