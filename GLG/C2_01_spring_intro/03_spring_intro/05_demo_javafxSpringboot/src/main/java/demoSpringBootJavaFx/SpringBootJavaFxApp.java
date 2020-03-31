package demoSpringBootJavaFx;

import java.io.InputStream;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import demoSpringBootJavaFx.baseSpringJavaFx.AbstractSpringBootAwareJavaFxApplication;
import javafx.application.Application;

/**
 * SpringBootJavaFxApp
 */
@SpringBootApplication
public class SpringBootJavaFxApp extends AbstractSpringBootAwareJavaFxApplication {

    public SpringBootJavaFxApp() {
       super(SpringBootJavaFxApp.class);
    }

    @Override
    protected InputStream getFxmlInputStream() {
        return getClass().getResourceAsStream("/app.fxml");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}