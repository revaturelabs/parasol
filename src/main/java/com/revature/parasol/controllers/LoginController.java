/**
 * 
 */
package com.revature.parasol.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marc
 *
 */
@EnableOAuth2Sso
@RestController
public class LoginController extends WebSecurityConfigurerAdapter{

    @Autowired
    Force force;
    
    @RequestMapping("/login")
    public Principal loginUser(OAuth2Authentication principal) {
	System.out.println(force.printRestUrl(principal));
	return principal;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .antMatcher("/**")
        .authorizeRequests()
          .antMatchers("/", "/login**", "/webjars/**", "/app.js", "/styling/angular-bootstrap.css",
        		  "/styling/page.css", "/styling/freelancer.min.css", "/styling/font-awesome.min.css",
        		  "/bootstrap/bootstrap.min.js", "/js/freelancer.min.js", "/services/AuthenticationService.js",
        		  "/services/ErrorService.js", "/services/UserService.js", "/landing/landing.js", 
        		  "/login/login.js", "/js/jqBootstrapValidation.js", "/revature_logo_1080.png")
          .permitAll()
        .anyRequest()
          .authenticated();
    }
    
}
