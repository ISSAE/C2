package glg203.aop.aspect;

import java.util.concurrent.*;

import org.springframework.stereotype.Component;

/**
 * AOPLog : permet de stocker des messages écrits par les aspects.
 */
@Component
public class AOPLog {
    
    private ConcurrentLinkedDeque<String> aopMessages = new ConcurrentLinkedDeque<>();

    public void addMessage(String message) {
        aopMessages.add(message);
    }

    public String popLastMessage() {
        return aopMessages.pollLast();
    }

    public String popFirstMessage() {
        return aopMessages.pollFirst();
    }

    
}