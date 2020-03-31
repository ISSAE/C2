package glg203.restclient;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import glg203.restclient.modele.Contact;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@PostConstruct
	public void run() {		
		RestTemplate restTemplate = restTemplateBuilder.build();
		// Usage simple.
		Contact contact = restTemplate.getForObject("http://localhost:8080/api/contact/0", Contact.class);
		System.out.println(contact);

		// Usage plus complexe (dû à l'effacement des génériques)
		// On crée une classe représentant le type qui nous intéresse.

		ParameterizedTypeReference<ArrayList<Contact>> responseType;
		responseType = new ParameterizedTypeReference<ArrayList<Contact>>() {
		};

		// Exchange est une rest template à tout faire.
		ResponseEntity<ArrayList<Contact>> res = restTemplate.exchange("http://localhost:8080/api/contact/",
				HttpMethod.GET, null, responseType);
		for (Contact c: res.getBody()) {
			System.out.println(c);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
