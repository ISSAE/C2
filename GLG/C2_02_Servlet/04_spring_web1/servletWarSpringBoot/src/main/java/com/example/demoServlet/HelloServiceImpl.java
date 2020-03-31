package com.example.demoServlet;

import org.springframework.stereotype.Component;

/**
 * HelloServiceImpl
 */
@Component
public class HelloServiceImpl implements HelloService{

    @Override
    public String hello() {
        return "Salut tout le monde !";
    }

    
}