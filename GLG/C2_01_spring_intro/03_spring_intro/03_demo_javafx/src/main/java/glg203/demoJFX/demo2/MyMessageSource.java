package glg203.demoJFX.demo2;


public class MyMessageSource implements IMessageSource {
    
    private static IMessageSource instance= new MyMessageSource();

    public static IMessageSource getInstance() {
        return instance;
    }
    
    @Override
    public String getMessage() {
        return "Dans cette version, on utilise un 'faux' singleton, la classe MessageSourceRepository.\n"
                + "Le couplage est moins apparent, mais il reste difficile de modifier le choix de l'implémentation"
                + "de IMessageSource.";
    }
    
}