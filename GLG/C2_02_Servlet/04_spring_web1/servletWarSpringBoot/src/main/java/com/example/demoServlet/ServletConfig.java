package com.example.demoServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.example.demoServlet")
public class ServletConfig {

    @Autowired
    private HelloServlet helloServlet;

    @Bean
    public ServletRegistrationBean resetServletRegistrationBean(){
        return new ServletRegistrationBean(helloServlet, "/hello");
    }
}
