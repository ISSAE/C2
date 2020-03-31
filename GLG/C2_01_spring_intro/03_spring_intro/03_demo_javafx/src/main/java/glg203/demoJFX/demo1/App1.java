package glg203.demoJFX.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Application utilisant un singleton.
 * @author rosmord
 */
public class App1 extends Application {

    public static void main(String[] args) {
        launch(App1.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/glg203/fxml/app1.fxml"));
        Scene scene = new Scene(root);    
        stage.setScene(scene);
        stage.show();
    } 
    
}