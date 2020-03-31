package demoSpringBootJavaFx.baseSpringJavaFx;

import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Une Application JavaFX qui utilise SpringBoot comme injecteur de dépendances.
 * 
 * <p>
 * Inspiré de https://spring.io/blog/2019/01/16/spring-tips-javafx.
 */
public  abstract class AbstractSpringBootAwareJavaFxApplication extends Application {

    private ConfigurableApplicationContext context;
    private Class<?> springApplicationClass;


    public AbstractSpringBootAwareJavaFxApplication(Class<?> springApplicationClass) {
        this.springApplicationClass = springApplicationClass;
    }

    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer = c -> {
            c.registerBean(Application.class, () -> AbstractSpringBootAwareJavaFxApplication.this);
            c.registerBean(Parameters.class, this::getParameters);
            c.registerBean(HostServices.class, this::getHostServices);
            c.registerBean(FXMLLoader.class, () -> buildFxmlLoader(c));
        };
        // Pseudo-constructeur...
        context = new SpringApplicationBuilder()
            .sources(springApplicationClass)
            .initializers(initializer)
            .run(getParameters().getRaw().toArray(new String[0]));
        super.init();
    }

    private static final FXMLLoader buildFxmlLoader(GenericApplicationContext c) {
        FXMLLoader loader= new FXMLLoader();
        loader.setControllerFactory(c::getBean);
        return loader;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    protected abstract InputStream getFxmlInputStream();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //context.publishEvent(new JavaFxAppStartedEvent(primaryStage));     
        FXMLLoader loader = context.getBean(FXMLLoader.class);
        Parent root = loader.load(getFxmlInputStream());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}