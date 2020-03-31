package com.example.simpleweb;

import org.springframework.stereotype.Service;

/**
 * CalculateurServiceImpl
 */
@Service
public class CalculateurServiceImpl implements ICalculateurService {

    @Override
    public double doubler(double x) {
       return 2*x;
    }

    
}