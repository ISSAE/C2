package glg203.demoJFX.demo3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Une Application JavaFX qui utilise Spring comme injecteur de dépendances.
 * 
 * Dans l'idéal, on aimerait que App3 fût un bean lui aussi. Le fonctionnement de <code>Application.launch</code>
 * rend ceci difficile.
 * 
 * <p>À défaut, on démarre donc Spring depuis App3. 
 * 
 * <p>C'est possible aussi avec des applications SpringBoot, voir https://spring.io/blog/2019/01/16/spring-tips-javafx.
 */
public class App3 extends Application {

    private FXMLLoader loader;
    private AnnotationConfigApplicationContext applicationContext;

    @Override
    public void init() throws Exception {        
        // Pseudo-constructeur...
        applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        this.loader= applicationContext.getBean(FXMLLoader.class);               
        super.init();
    }

    @Override
    public void stop() throws Exception {        
        super.stop();
        applicationContext.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= loader.load(getClass().getResource("/glg203/fxml/app.fxml").openStream());
        Scene scene = new Scene(root);    
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
    
}