package glg203.demoJFX.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Seconde application, avec un singleton plus évolué.
 * 
 * @author rosmord
 */
public class App3 extends Application {

    public static void main(String[] args) {
        launch(App3.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SimpleDirectory.getInstance().register("MessageSource", new MyMessageSource());
        
        Parent root = FXMLLoader.load(getClass().getResource("/glg203/fxml/app3.fxml"));
        Scene scene = new Scene(root);    
        stage.setScene(scene);
        stage.show();
    } 
    
}