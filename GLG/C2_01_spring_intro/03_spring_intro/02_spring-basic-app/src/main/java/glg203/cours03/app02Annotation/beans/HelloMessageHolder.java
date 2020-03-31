package glg203.cours03.app02Annotation.beans;

import org.springframework.stereotype.Component;

/**
 * HelloMessageHolder
 */
@Component
public class HelloMessageHolder implements IMessageHolder {
    private final String message;
    public HelloMessageHolder() {
        this.message = "Composant créé par introspection";
    }
    @Override
    public String getMessage() {
        return message;
    }
}