package glg203.securityDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration commentée de la sécurité.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
			.mvcMatchers("/protegee").authenticated()
		.and()
			.formLogin()
				.loginPage("/login") 
				.failureUrl("/login-error")
		
		;
		// @formatter:on
	}


}
