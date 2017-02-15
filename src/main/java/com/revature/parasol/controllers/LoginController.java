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

    /**
     * When /auth/login is called, the user is automatically redirected to salesforce if no
     * OAuth2Authentication object is detected by Spring Security. Once they have logged in, this method is run.
     * <br>
     * When this method is run, the user's role and the modules the can access are appended to the details
     * of the Authentication Object and the whole authentication object is sent to the frontend.
     * <br> 
     * At a later date, the return value should be changed such that it only returns information relevant to
     * the frontend, not the entire OAuth2Authentication object.
     * <br> <br>
     * @param code occasionally Salesforce will send this along, but it's not important.
     * @param authentication
     * @return
     * @throws IOException
     */
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

    /**
     * Gets the role and accessible modules for the user. 
     * <br> <br>
     * This is an important step in logging the user in. The user need to have access to modules based on
     * the role they have in the database. The role and modules therefore need to be passed to the frontend 
     * and processed in a way which allows the user to navigate them.
     * <br> <br>
     * @param authentication an OAuth2Authentication object, the user's authentication
     * @return
     */
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
