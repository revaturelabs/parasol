package com.revature.parasol.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class DefaultRoutingController {
    @RequestMapping(value = {"/", "/otherVariousURLs"} )
    public String routeToHome(){
        return "forward:index.html";
    }
    
    @RequestMapping(value = "/logout")
    public String doLogout(HttpServletRequest req, Principal principal) {
    	//invalidate the current session
    	req.getSession().invalidate();
    	
    	//Grab the token and revoke it
    	OAuth2Authentication auth = (OAuth2Authentication) principal;
//    	Map<String, Object> details = (HashMap<String, Object>) auth.getDetails();
//    	String token = (String) details.get("tokenValue");
    	
    	//erase the credentials?
    	auth.eraseCredentials();
    	auth.setAuthenticated(false);

    	return "redirect:index.html";
    }
}
