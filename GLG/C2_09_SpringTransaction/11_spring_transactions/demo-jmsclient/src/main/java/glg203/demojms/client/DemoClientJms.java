package glg203.demojms.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement activé dans Spring boot par la présence de spring-boot-starter-activemq
//@EnableJms // (voir README)
public class DemoClientJms {

	@Autowired
	MessageSenderService service;

	@PostConstruct
	public void runMe() {
		try {
			service.sendMessages("premier message ok !", "second message ok");
			service.sendMessages("ne devrait pas être reçu", "");
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoClientJms.class, args);
	}

}
