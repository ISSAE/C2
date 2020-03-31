package com.example.jsoncsrf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * On publie l'authentication manager pour injection dans LoginController.
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("user").password(encoder.encode("user")).roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**").antMatchers("/webjars/**").antMatchers("/index.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Grandes manœuvres ici, pour gérer le login directement (avec AJAX)
        //
        //
        // On a plusieurs possibilités :
        // a) tout gérer nous-mêmes (probablement la meilleure option...)
        // b) modifier le comportement par défaut du login
        // c) utiliser httpBasic, qui permettrait d'être stateless (on enverrait
        // systématiquement le login et le password)
        //
        // Nous avons commencé ici en utilisant la solution b)
        // mais elle est assez pénible à mettre en œuvre.
        // La version actuelle est donc a), qui est bien plus simple.
        http.httpBasic().disable().logout().disable().formLogin().disable().authorizeRequests()
                // On autorise le put uniquement aux utilisateurs connectés
                .mvcMatchers(HttpMethod.PUT, "/**").authenticated()
                // Le reste est autorisé à tout le monde.
                .mvcMatchers("/**").permitAll();
    }
}