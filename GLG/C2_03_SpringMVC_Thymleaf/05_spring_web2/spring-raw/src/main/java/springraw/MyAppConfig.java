package springraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Configuration d'une application Spring MVC utilisant Thymeleaf.
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class MyAppConfig implements WebMvcConfigurer {

	/**
	 * Injection de l'application Context,
	 * nécessaire pour Thymeleaf.
	 */
	@Autowired
	ApplicationContext applicationContext;


	/**
	 * Il n'y a pas que des templates Thymeleaf dans la vie.
	 * Une application peut utiliser des pages fixes, des images, etc...
	 * Configure l'accès direct à certains types de ressources.
	 *
	 * <p>Noter que cette méthode est une méthode de WebMvcConfigurer. Elle ne définit pas un bean.</p>
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**/*.html").addResourceLocations("/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	}

	/**
	 * Le View resolver associe un nom de vue à une vue effective.
	 * Il est nécessaire pour toute application Spring MVC, indépendamment du moteur
	 * utilisé.
	 * Ici, il renverra des vues vers Thymeleaf.
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(){
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    // On doit configurer le moteur de templates de Thymeleaf.
	    viewResolver.setTemplateEngine(templateEngine());
	    return viewResolver;
	}

	/**
	 * Le moteur de templates de Thymeleaf.
	 * On le configure avec un SpringResourceTemplateResolver
	 * pour qu'il sache où chercher les templates.
	 * 
	 * @return
	 */
	@Bean
	public SpringTemplateEngine templateEngine(){
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver()); // Voir ci-dessous
		// Activer la ligne ci-dessous si les expressions SpringEL utilisées ne risquent pas de changer de type.
	    // templateEngine.setEnableSpringELCompiler(true);
	    return templateEngine;
	}

	/**
	 * Cet objet permet de dire à Thymeleaf où il trouvera ses templates.
	 * (dans /WEB-INF/templates, suffixe: ".html")
	 * @return
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver(){
		// La notion de templates est spécifique à Thymeleaf.
		// On utilise le template Resolver spécial pour Spring
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(this.applicationContext);
	    templateResolver.setPrefix("/WEB-INF/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setCacheable(true);
	    return templateResolver;
	}


}
