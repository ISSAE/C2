package glg203.cours03.app01Annotation;

/**
 * HelloMessageHolder
 */
public class HelloMessageHolder implements IMessageHolder {
    private final String message;
    public HelloMessageHolder(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}