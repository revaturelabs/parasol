/**
 * 
 */
package com.revature.parasol.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marc
 *
 */
@RestController
public class LoginController{

    @Autowired
    Force force;
    
    @RequestMapping("/login")
    public Principal loginUser(OAuth2Authentication principal) {
		System.out.println(force.printRestUrl(principal));
		return principal;
    }
    
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//    	web.ignoring().antMatchers("/webjars/**", "/styling/**", "/public/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/", "/login")
//				.permitAll()
//				.anyRequest()
//				.authenticated()
//				.and()
//			.logout()
//				.logoutSuccessUrl("/")
//				.permitAll()
//				.and()
//			.csrf()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    }
}
