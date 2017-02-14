/**
 * 
 */
package com.revature.parasol.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.parasol.domain.service.RoleModuleServiceInterface;

/**
 * @author Marc Kuniansky
 *
 */
@Controller
@RequestMapping(value = "/auth")
public class LoginController {


    @Autowired
    RoleModuleServiceInterface roleModuleService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public void loginUser(@RequestParam(required = false) String code, OAuth2Authentication authentication,
	    HttpServletResponse resp) throws IOException {

	System.out.println("Inside of login user...");
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	String token = details.getTokenValue();
	
	System.out.println("Details: " + details);
	
	
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

    @RequestMapping(value = "/rolesandmodules", method=RequestMethod.GET)
    @ResponseBody
    public LinkedHashMap<Object, Object> getRolesAndModules(OAuth2Authentication authentication) {

	LinkedHashMap<Object, Object> userAuthDetails = (LinkedHashMap<Object, Object>) authentication
		.getUserAuthentication().getDetails();
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	
	String token = details.getTokenValue();
	String userUrl = (String) userAuthDetails.get("sub");

	String role = roleModuleService.getRoleForUser(userUrl, token);
	Object moduleList = roleModuleService.getModulesForRole(role);

	userAuthDetails.put("role", role);
	userAuthDetails.put("modules", moduleList);

	return userAuthDetails;
	
    }

}
