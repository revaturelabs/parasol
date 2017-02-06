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
          .antMatchers("/", "/login**", "/webjars/**")
          .permitAll()
        .anyRequest()
          .authenticated();
    }
    
}
