package glg203.cours03.app00Xml;

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