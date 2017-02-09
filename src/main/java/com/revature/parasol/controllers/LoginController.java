/**
 * 
 */
package com.revature.parasol.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marc
 *
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    Force force;

    @RequestMapping("/sflogin")
    public Principal loginUser(@RequestParam(required=false)String code, OAuth2Authentication principal) {
	System.out.println("Hello world!");

	System.out.println(force.printRestUrl(principal));
	System.out.println("Principal: " + principal.toString());
	return principal;
    }
}
