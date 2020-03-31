package glg203.demoJFX.demo2;

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
public class App2 extends Application {

    public static void main(String[] args) {
        launch(App2.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/glg203/fxml/app2.fxml"));
        Scene scene = new Scene(root);    
        stage.setScene(scene);
        stage.show();
    } 
    
}