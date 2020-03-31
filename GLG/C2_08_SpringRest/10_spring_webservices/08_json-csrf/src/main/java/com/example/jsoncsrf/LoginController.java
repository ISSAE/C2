package com.example.jsoncsrf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * LoginController.
 * 
 * Un contrôleur pour le login par AJAX. Pas de redirection ou quoi que ce soit
 * de ce genre: on travaille directement en Javascript, et on ne veut pas être
 * renvoyé sur quelque page que ce soit par Spring.
 */
@Controller
public class LoginController {

    @Autowired
    AuthenticationManager manager;

    @PostMapping("/login")
    public void login(String username, String password, HttpServletResponse response, HttpServletRequest request)
            throws IOException, ServletException {
        // try {
        request.login(username, password); // suffit...
        // mode manuel complet :
        // Authentication authentication = new
        // UsernamePasswordAuthenticationToken(username, password);
        // Authentication result = manager.authenticate(authentication);
        // SecurityContextHolder.getContext().setAuthentication(result);
        response.getWriter().append("ok");
        response.setStatus(200);
        // } catch (AuthenticationException e) {
        // SecurityContextHolder.getContext().setAuthentication(null);
        // request.logout(); - ne fonctionne pas ?
        // response.getWriter().append("échec");
        // response.setStatus(401);
        // }
    }

    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) throws ServletException {
        // request.logout(); ne fonctionne pas ?
        SecurityContextHolder.getContext().setAuthentication(null);
        return "logout";
    }
}