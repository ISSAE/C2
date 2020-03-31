package glg203.securityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration commentée de la sécurité.
 * <p> Ne pas hésiter à regarder la documentation de la classe {@link WebSecurityConfigurerAdapter}
 */
@Configuration
// @EnableWebSecurity inutile
@EnableGlobalMethodSecurity( // Active toutes les annotations sur les contrôleurs.	
	prePostEnabled = true, 
	securedEnabled = true, 
	jsr250Enabled = true
)  
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	/**
	 * On met en place cet encoder pour pouvoir l'utiliser quand on veut sauver des objets utilisateurs.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/**
	 * Important : configure l'authenticationManager.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	/**
	 * Cette méthode est surtout intéressante parce qu'elle permet de
	 * "court-circuiter" Spring Security.
	 * 
	 * <p>
	 * On l'utilise principalement pour désactiver les tests sur certaines URL
	 * <em>avant</em> qu'on n'en arrive à vérifier en profondeur les choses.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// @formatter:off 
		web.ignoring()
			.antMatchers("/css/**");
		// @formatter:on
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {							
		// @formatter:off 
		http
		.authorizeRequests()					
			.mvcMatchers("/admin/**").hasRole("ADMIN")	// réserve /admin et ses fils aux utilisateurs dont le rôle est ADMIN.
			.mvcMatchers("/zoneConnectee").authenticated() //  cette partie est 
														   // exclusivement réservée aux utilisateurs connectés.
			.anyRequest().permitAll()
		.and()
			.formLogin()			
		.and()
			.anonymous() // par défaut
						 // ce que ça fait : un utilisateur non connecté a automatiquement le rôle "ROLE_ANONYMOUS"
						 // ce rôle peut être utilisé dans des règles (voir Demo#anonyme)
		//.and()
		//	.exceptionHandling()
		//		.accessDeniedPage("/e403")
		//  .csrf().disable() // cela désactiverait la protection csrf!!
		// .authorizeRequests().mvcMatchers("/zoneConnectee").not().hasAnyRole("ADMIN") interdirait zoneConnectee à admin...		
		;
		// @formatter:on
	}


}
