/**
 * 
 */
package com.revature.parasol.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marc
 *
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    Force force;

    @RequestMapping("/login")
    public Principal loginUser(@RequestParam(required=false)String code, OAuth2Authentication principal) {
	System.out.println(force.printRestUrl(principal));
	System.out.println("Principal: " + principal.toString() + "\n");
	System.out.println("Name: " + principal.getName() + "\n");
	System.out.println("Details: " + principal.getDetails() + "\n");
	System.out.println("Credentials: " + principal.getCredentials() + "\n");
	System.out.println("Authorities: " + principal.getAuthorities() + "\n");
	System.out.println("User auth: " + principal.getUserAuthentication() + "\n");
	System.out.println("OauthRequest: " + principal.getOAuth2Request() + "\n");
	
	Map<String, Object> map = (Map<String, Object>) principal.getDetails();
	System.out.println(map.toString());
	System.out.println("Hopefully getting the sub header: " + map.get("sub"));
	
	return principal;
    }
}
