# Demo Springboot plus JavaFx.

 Inspiré de https://spring.io/blog/2019/01/16/spring-tips-javafx.

Dans un premier temps, pour que le programmeur comprenne bien à quel moment il peut mettre en place les objets, nous avons supprimé l'utilisation d'un événement et nous l'avons remplacé par une factory method.

## Utilisation:

La base de l'application repose sur deux classes:

La première est annotée avec @SpringBootApplication. Elle ne sera pas le main, mais sert de configuration principale (souvent elle est relativement, voire complètement vide.)

La seconde étend AbstractSpringBootAwareJavaFxApplication.

Elle doit avoir un constructeur par défaut, et fournir par ailleurs la ressource FXML qui servira à charger la fenêtre principale.

D'où le code :
~~~~~java
public class SpringBootJavaFxApp extends AbstractSpringBootAwareJavaFxApplication {

    public SpringBootJavaFxApp() {
        // Important: passe la classe annotée avec @SpringBootApplication:
        super(DemoApplication.class);
    }

    /**
     *  Description de la fenêtre de base.
    */ 
    @Override
    protected InputStream getFxmlInputStream() {
        return getClass().getResourceAsStream("/app.fxml");
    }

    /**
    * Lancement de l'application.
    */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
~~~~~

Le reste fonctionne normalement.
