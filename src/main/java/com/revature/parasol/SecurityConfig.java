/**
 * 
 */
package com.revature.parasol;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author Marc
 *
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/webjars/**", "/bootstrap/", "/styling/**", "/landing/**","/welcome/**", "/services/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/login", "/app.js", "/**.jpg", "/**.png", "/**.js")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.permitAll()
				.and()
			.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
