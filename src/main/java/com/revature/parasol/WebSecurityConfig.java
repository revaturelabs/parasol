package com.revature.parasol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/home", "/","/auth/salesforce/callback").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/auth/salesforce")
                .permitAll()
                .and()
            .logout()
                .permitAll();
      
//        http
//        .httpBasic()
//      .and()
//        .authorizeRequests()
//          .antMatchers("/index.html", "/hello.html", "/login.html", "/").permitAll()
//          .anyRequest().authenticated();
    }
	
	/*
	 *Ignoring css files and javascript files
	 */
	@Override
	public void configure(WebSecurity security) {
		security.ignoring().antMatchers("/css/**","/js/**");
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}