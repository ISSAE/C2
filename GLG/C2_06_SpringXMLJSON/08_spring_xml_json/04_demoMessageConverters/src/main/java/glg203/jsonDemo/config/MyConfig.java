package glg203.jsonDemo.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import glg203.jsonDemo.utils.PersonneConverter;

/**
 * MyConfig.
 * 
 * Configuration des Message Converters.
 * Autre possibilité : redéfinir configureMessageConverters
 * 
 */
@Configuration
public class MyConfig implements WebMvcConfigurer{

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
       converters.add(new BufferedImageHttpMessageConverter());
       converters.add(new PersonneConverter());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }
}