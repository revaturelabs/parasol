package com.revature.parasol.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Permissions;
import com.revature.parasol.domain.Roles;
import com.revature.parasol.service.PermissionsService;

/**
 * @author Vincent
 *
 */
@RestController
//@EnableAuthorizationServer
//@EnableResourceServer  //secures everything in an authorization server except the "/oauth/*" endpoints.
public class AuthController{

    @Autowired
    private Force force;
    
    @Autowired
	PermissionsService ps;
    
    //Return user's credential if logged in
    //https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2-vanilla
    @RequestMapping(value = "/User" )
    public Principal userInfo(Principal principal){
        return principal;
    }
    
    @RequestMapping(value = "/modules")
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
}
