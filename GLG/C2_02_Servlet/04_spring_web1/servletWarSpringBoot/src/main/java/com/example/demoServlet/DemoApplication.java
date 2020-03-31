package com.example.demoServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//	@Bean
	//public ServletRegistrationBean delegateServiceExporterServlet() {
    //return new ServletRegistrationBean(new DelegateServiceExporter(), "/remoting/DelegateService");
	//}

}
