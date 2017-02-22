/**
 * 
 */
package com.revature.parasol.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Permissions;
import com.revature.parasol.domain.Roles;
import com.revature.parasol.domain.service.PermissionsService;


/**
 * @author Marc Kuniansky
 *
 */
@Controller
public class LoginController {

	//Billy Code Added
	@Autowired
	Force force;
	@Autowired
	PermissionsService ps;

	@RequestMapping(value = "/modules")
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
			if (force.healthCheck(p.getModule().getModuleURL())) {
				mod.add(p.getModule());
			}
		}
		
		//Returns modules and admin status
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("modules", mod);
    	map.put("admin", Force.isAdmin(roleName));
		return map;
	}
	
	//Logging out - Ramiz
	@RequestMapping(value = "/logout")
	public String DoLogout(HttpServletRequest request, HttpServletResponse response){
		//http://docs.spring.io/spring-security/site/xref/org/springframework/security/ui/logout/SecurityContextLogoutHandler.html
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
	    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
	    cookieClearingLogoutHandler.logout(request, response, null);
	    securityContextLogoutHandler.logout(request, response, null);
		request.getSession(false);

		return "redirect:/";
	}
}
