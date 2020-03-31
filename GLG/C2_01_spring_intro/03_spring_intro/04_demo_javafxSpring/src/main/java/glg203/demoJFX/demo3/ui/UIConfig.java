package glg203.demoJFX.demo3.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javafx.fxml.FXMLLoader;

/**
 * Configuration Spring pour la partie interface graphique.
 * Permet essentiellement de fournir un FXMLLoader qui utilise Spring pour créer les contrôleurs.
 */
@Configuration
public class UIConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    FXMLLoader fxmlLoader() {
        // Configure le loader pour qu'il utilise Spring.
        FXMLLoader loader= new FXMLLoader();
        loader.setControllerFactory(applicationContext::getBean);
        return loader;
    }
    
}