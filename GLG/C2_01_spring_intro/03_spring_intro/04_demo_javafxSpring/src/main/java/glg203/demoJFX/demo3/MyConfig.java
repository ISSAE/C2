package glg203.demoJFX.demo3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Une configuration "presque" vide.
 * Elle déclenche quand même le scan de composants, et
 * permet de trouver la sous-configuration qui fournit le FXMLLoader.
 */
@Configuration
@ComponentScan
public class MyConfig { 
}