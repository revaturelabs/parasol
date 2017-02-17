/**
 * 
 */
package com.revature.parasol.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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

	//Billy Code Added
	@Autowired
	Force force;

	@Autowired
	PermissionsService ps;


	@RequestMapping(value = "/login")
	@ResponseBody
	public Map<String, Object> getModules(OAuth2Authentication principal) {
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
		
		//Returns modules and admin status
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("modules", mod);
    	map.put("admin", Force.isAdmin(roleName));
		return map;
	}

}
