package com.example.oauth2;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DemoController
 */
@Controller
public class DemoController {

    @GetMapping(path = { "/", "/demo" })
    public String home(Model model, Principal principal) {
        model.addAttribute("user", principal);                    
        return "home";
    }

    @GetMapping("/user")  
    @ResponseBody      
    public OAuth2User getUser(OAuth2AuthenticationToken tok) {
        return tok.getPrincipal();
    }

}