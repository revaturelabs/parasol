/**
 * 
 */
package com.revature.parasol.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marc
 *
 */
@RestController
@RequestMapping(value = "/auth")
public class LoginController {

    @Autowired
    Force force;

    // @Autowired
    // RoleModuleServiceInterface roleModuleService;

    @RequestMapping(value = "/login")
    public String loginUser(@RequestParam(required = false) String code, OAuth2Authentication authentication,
	    HttpServletRequest request, HttpServletResponse response) throws IOException {

	// Get the role and modules that the user is allowed to access
	// I THINK ALL OF THIS NEEDS TO GO ANYWHERE WHERE YOU NEED TO GET THE
	// ROLE OR MODULES
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	String token = details.getTokenValue();

	JSONObject json = new JSONObject();
	try {
	    json.put("token", token);
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	String forwardString = "forward:index.html" + "?token=" + token;

	response.sendRedirect("https://dev.parasol.revature.pro/index.html?token=" + token);
	return forwardString;
    }

    @RequestMapping(value = "/rolesandmodules")
    @ResponseBody
    public String getRolesAndModules(String token) {

	OAuth2Authentication principal = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();

	LinkedHashMap<Object, Object> userAuthDetails = (LinkedHashMap<Object, Object>) principal
		.getUserAuthentication().getDetails();
	String userUrl = (String) userAuthDetails.get("sub");

	// String role = roleModuleService.getRoleForUser(userUrl, token);
	// Object moduleList = roleModuleService.getModulesForRole(role);

	userAuthDetails.put("role", "role");
	userAuthDetails.put("modules", "moduleList");

	JSONObject json = new JSONObject();
	try {
	    json.put("role", "role");
	    json.put("modules", "moduleList");
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return json.toString();
    }

}
