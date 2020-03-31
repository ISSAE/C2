package glg203.cours03.app02Annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyConfig
 */
@Configuration
@ComponentScan(basePackageClasses = {App.class})
public class MyConfig {   
}