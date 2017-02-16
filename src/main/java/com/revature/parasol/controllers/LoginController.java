/**
 * 
 */
package com.revature.parasol.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Roles;
import com.revature.parasol.domain.Permissions;

import com.revature.parasol.domain.service.PermissionsService;


import org.springframework.beans.factory.annotation.Autowired;
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

	//Billy Code Added
	@Autowired
	Force force;

	@Autowired
	PermissionsService ps;


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
	public List<Modules> getModules(OAuth2Authentication principal) {
		//Gets the role name from Salesforce and create a new instance of Role
		String roleName = force.getRoleName(principal);
		Roles role = new Roles(roleName);

		//List of modules to be returned
		List<Modules> mod = new ArrayList<>();

		//Gets the list of permissions user has access to
		List<Permissions> pList = ps.findByRole(role);
		//Populate module list
		for (Permissions p : pList) {
			mod.add(p.getModule());
		}
		return mod;
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


	//System.out.println("Inside Roles and Modules");

	
	//Get user details from the authentication object
	//Within the array we will store role and module list
	LinkedHashMap<Object, Object> userAuthDetails = (LinkedHashMap<Object, Object>) authentication
		.getUserAuthentication().getDetails();
	

	//System.out.println("User details: " + userAuthDetails.toString());


	//Get the token for sending to DAO so REST calls can be made to salesforce
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

	
	String token = details.getTokenValue();

	//System.out.println("Token: " + token);
	String userId = (String) userAuthDetails.get("user_id");
	//System.out.println("User id: " + userId);



	////////////////////////////////////////////////////////
	//CODE BILLY ADDED TO GET SALESFORCE USER ROLE - BEGIN//
	////////////////////////////////////////////////////////

	String role = force.getRoleName(authentication);
	////////////////////////////////////////////////////////
	//CODE BILLY ADDED TO GET SALESFORCE USER ROLE - END  //
	////////////////////////////////////////////////////////


	/* IMPLEMENT THE AUTOWIRED INTERFACE AND MAKE THE TWO METHODS BELOW WORK */
	// String role = roleModuleService.getRoleForUser(userId, token);
	// Object moduleList = roleModuleService.getModulesForRole(role);

	List<HashMap<String, String>> modules = new ArrayList<HashMap<String, String>>();
	for (int i = 0; i < 2; i++) {
		HashMap<String, String> wow = new HashMap<String, String>();
		wow.put("moduleName", "Google" + i);
		wow.put("moduleURL", "https://www.google.com/");
		modules.add(wow);
	}

	userAuthDetails.put("role", "admin");
	userAuthDetails.put("modules", modules);

	return userAuthDetails;

    }

}
