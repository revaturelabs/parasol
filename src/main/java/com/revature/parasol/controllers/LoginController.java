/**
 * 
 */
package com.revature.parasol.controllers;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.parasol.domain.service.RoleModuleServiceInterface;

/**
 * @author Marc
 *
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    Force force;

    @Autowired
    RoleModuleServiceInterface roleModuleService;

    @RequestMapping("/login")
    public JSONObject loginUser(@RequestParam(required = false) String code, OAuth2Authentication authentication) {

	//Get the role and modules that the user is allowed to access
	//I THINK ALL OF THIS NEEDS TO GO ANYWHERE WHERE YOU NEED TO GET THE ROLE OR MODULES
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	String token = details.getTokenValue();
	
	LinkedHashMap<Object, Object> userAuthDetails = (LinkedHashMap<Object, Object>) authentication
		.getUserAuthentication().getDetails();
	String userUrl = (String) userAuthDetails.get("sub");
	
	String role = roleModuleService.getRoleForUser(userUrl, token);
	Object moduleList = roleModuleService.getModulesForRole(role);
	userAuthDetails.put("role", role);
	userAuthDetails.put("modules", moduleList);
	

	JSONObject json = new JSONObject();
	try {
	    json.put("token", token);
	    json.put("role", role);
	    json.put("modules", moduleList);
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	System.out.println(force.printRestUrl(authentication));
	System.out.println("Principal: " + authentication.toString() + "\n");
	System.out.println("Name: " + authentication.getName() + "\n");
	System.out.println("Details: " + authentication.getDetails() + "\n");
	System.out.println("Credentials: " + authentication.getCredentials() + "\n");
	System.out.println("Authorities: " + authentication.getAuthorities() + "\n");
	System.out.println("User auth: " + authentication.getUserAuthentication() + "\n");
	System.out.println("OauthRequest: " + authentication.getOAuth2Request() + "\n");

	Object obj = authentication.getUserAuthentication().getDetails();
	Object obj2 = authentication.getDetails();

	System.out.println("CLASS OF THE ORIGINAL OBJECT: " + obj2.getClass());
	System.out.println("CLASS OF THE OBJECT IN ALL CAPS: " + obj.getClass());
	LinkedHashMap<Object, Object> map = (LinkedHashMap<Object, Object>) authentication.getUserAuthentication()
		.getDetails();
	System.out.println("Map: " + map.toString() + "\n");
	System.out.println("Key set of map: " + map.keySet().toString() + "\n");

	System.out.println("Token: " + details.getTokenValue());

	return json;
    }
}
