package com.example.simpleweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * HelloServlet
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

    @Autowired
    ICalculateurService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double val = Double.parseDouble(req.getParameter("x"));
        resp.getWriter().println("Double de notre nombre " + service.doubler(val));
    }
    
    
}