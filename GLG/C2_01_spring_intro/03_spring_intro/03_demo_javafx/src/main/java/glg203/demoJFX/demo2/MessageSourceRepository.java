package glg203.demoJFX.demo2;


/**
 * Un (quasi) singleton pour passer le "bon" IMessageSource.
 * <p> Pas très très pratique.
 */
public class MessageSourceRepository {

    private static final IMessageSource instance= new MyMessageSource();

    /**
     * @return the instance
     */
    public static IMessageSource getInstance() {
        return instance;
    }
    
}