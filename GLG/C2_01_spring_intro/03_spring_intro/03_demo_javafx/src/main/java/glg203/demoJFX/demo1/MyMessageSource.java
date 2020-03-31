package glg203.demoJFX.demo1;

public class MyMessageSource implements IMessageSource {
    
    private static IMessageSource instance= new MyMessageSource();

    public static IMessageSource getInstance() {
        return instance;
    }
    
    @Override
    public String getMessage() {
        return "Cette application montre le problème qu'on à  passer "
                + "une source de messages à  une application JavaFx.\n"+
        "Comme l'application la méthode FXMLLoader.load instancie "
                + "le App1Controller, on ne peut pas facilement passer de "
                + "données à celui-ci...\n"
                + "Ici, le contrôleur utilise directement le MyMessageSource...";
    }
    
}