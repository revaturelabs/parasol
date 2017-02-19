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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Roles;
import com.revature.parasol.domain.Permissions;

import com.revature.parasol.domain.service.PermissionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
	
	//Logging out - Ramiz
	@RequestMapping(value = "/logout")
	public void DoLogout(HttpServletRequest request, HttpServletResponse response){
	   
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
	    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
	    cookieClearingLogoutHandler.logout(request, response, null);
	    securityContextLogoutHandler.logout(request, response, null);
		HttpSession session = request.getSession(false);
		     
		//clear spring security context
//		SecurityContextHolder.clearContext();
//		       
//		session = request.getSession(false);
   
		//Invalidate session
//		if(session != null) {
//			session.invalidate();
//		}

		//Clear Cookies
//		for(Cookie cookie : request.getCookies()) {
//			cookie.setMaxAge(0);
//		}
		
		//return "redirect:index.html";
	}
}
