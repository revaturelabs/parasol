/**
 * 
 */
package com.revature.parasol.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Marc Kuniansky
 *
 */
@Controller
@RequestMapping(value = "/auth")
public class LoginController {

    // @Autowired
    // RoleModuleServiceInterface roleModuleService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public OAuth2Authentication loginUser(@RequestParam(required = false) String code,
	    OAuth2Authentication authentication) throws IOException {

	//Get the details, including the token
	System.out.println("Inside of login user...");
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

	System.out.println("Details: " + details);

	//Get roles and modules
	getRolesAndModules(authentication);
	System.out.println("User details after additions: " + authentication.getUserAuthentication().getDetails());

	System.out.println("Returned object: " + authentication.toString());
	// resp.sendRedirect("https://dev.parasol.revature.pro/?token=" +
	// token);
	return authentication;
    }

    public LinkedHashMap<Object, Object> getRolesAndModules(OAuth2Authentication authentication) {

	System.out.println("Inside Roles and Modules");
	
	//Get user details from the authentication object
	//Within the array we will store role and module list
	LinkedHashMap<Object, Object> userAuthDetails = (LinkedHashMap<Object, Object>) authentication
		.getUserAuthentication().getDetails();
	
	System.out.println("User details: " + userAuthDetails.toString());

	//Get the token for sending to DAO so REST calls can be made to salesforce
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

	
	String token = details.getTokenValue();
	System.out.println("Token: " + token);
	String userId = (String) userAuthDetails.get("user_id");
	System.out.println("User id: " + userId);

	/* IMPLEMENT THE AUTOWIRED INTERFACE AND MAKE THE TWO METHODS BELOW WORK */
	// String role = roleModuleService.getRoleForUser(userId, token);
	// Object moduleList = roleModuleService.getModulesForRole(role);

	HashMap<String, String> modules = new HashMap<String, String>();
	modules.put("Google", "https://www.google.com/");
	modules.put("Reddit", "https://www.reddit.com/");

	userAuthDetails.put("role", "admin");
	userAuthDetails.put("modules", modules);

	return userAuthDetails;

    }

}
