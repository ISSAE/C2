package com.example.jsoncsrf;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MessageController
 */
@RestController
public class MessageController {

    private AtomicReference<String> message = new AtomicReference<String>("bonjour");
    
    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
    
    @GetMapping("/api/message")
    public String getMessage() {
        return message.get();    
    }

    @PutMapping(value = "/api/message")    
    public void setMessage(String message) {
        this.message.set(message);
    }
}