package glg203.demoJFX.demo4;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * App3
 * 
 * Le problème ici... On souhaiterait passer
 */

public class App4 extends Application {

    /**
     * On simule ici un injecteur basique de dépendances...
     */

    private Map<Class<?>, Object> beansMap= new HashMap<>();

    private Callback<Class<?>, Object> myControllerFactory = clazz -> buildObject(clazz);


    public App4() {
        prepareBeans();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/glg203/fxml/app4.fxml"));
        loader.setControllerFactory(myControllerFactory);
        Parent root = loader.load();
        Scene scene = new Scene(root);    
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Injecteur de dépendances très très basique...
     */
    private Object buildObject(Class<?> clazz) {
        return beansMap.get(clazz);
    }
    
    /**
     * Mets en place les beans pour l'injection de dépendance...
     */
    private void prepareBeans() {
        App4Controller app2Controller = new App4Controller(new MyMessageSource());
        beansMap.put(App4Controller.class, app2Controller);
    }

    public static void main(String[] args) {
        launch(App4.class, args);
    }



}