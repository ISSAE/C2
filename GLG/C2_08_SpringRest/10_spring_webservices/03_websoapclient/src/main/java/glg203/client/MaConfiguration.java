package glg203.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import glg203.client.PublicationsClient;

/**
 * MaConfiguration
 */
@Configuration
public class MaConfiguration {

    
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // this package must match the package in the <generatePackage> 
    marshaller.setContextPath("fr.cnam.glg203.publications");
    return marshaller;
  }

  @Bean
  public PublicationsClient publicationsClient(Jaxb2Marshaller marshaller) {
    PublicationsClient client = new PublicationsClient();
    client.setDefaultUri("http://localhost:8080/ws");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }

}