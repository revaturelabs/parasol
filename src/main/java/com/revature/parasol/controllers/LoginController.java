/**
 * 
 */
package com.revature.parasol.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Marc Kuniansky
 *
 */
@Controller
@RequestMapping(value = "/auth")
public class LoginController {


    //@Autowired
    //RoleModuleServiceInterface roleModuleService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public void loginUser(@RequestParam(required = false) String code, OAuth2Authentication authentication,
	    HttpServletResponse resp) throws IOException {

	System.out.println("Inside of login user...");
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	String token = details.getTokenValue();
	
	System.out.println("Details: " + details);
	
	LinkedHashMap<Object, Object> userAuthDetails = getRolesAndModules(authentication);
	System.out.println("User details after additions: " + userAuthDetails);
	
	JSONObject json = new JSONObject();
	try {
	    json.put("token", token);
	} catch (JSONException e) {
	    e.printStackTrace();
	    Logger.getRootLogger().error(e);
	}

	SecurityContextHolder.getContext().getAuthentication().getPrincipal();


	resp.sendRedirect("https://dev.parasol.revature.pro/?token=" + token);
	
    }

    public LinkedHashMap<Object, Object> getRolesAndModules(OAuth2Authentication authentication) {

	System.out.println("Inside Roles and Modules");
	LinkedHashMap<Object, Object> userAuthDetails = (LinkedHashMap<Object, Object>) authentication
		.getUserAuthentication().getDetails();
	System.out.println("User details: " + userAuthDetails.toString());
	
	
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	
	String token = details.getTokenValue();
	System.out.println("Token: " + token);
	String userId = (String) userAuthDetails.get("user_id");
	System.out.println("User id: " + userId);
	

	//String role = roleModuleService.getRoleForUser(userUrl, token);
	//Object moduleList = roleModuleService.getModulesForRole(role);

	userAuthDetails.put("role", "role");
	userAuthDetails.put("modules", "moduleList");

	return userAuthDetails;
	
    }

}
