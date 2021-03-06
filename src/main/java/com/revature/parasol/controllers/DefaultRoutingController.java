package com.revature.parasol.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.parasol.domain.dto.ModuleRegDTO;
import com.revature.parasol.domain.service.PermissionsService;

@Controller
public class DefaultRoutingController {
	
	@Autowired
	PermissionsService ps;
	
    @RequestMapping(value = "/")
    public String routeToHome(){
        return "forward:index.html";
    }
    
    @RequestMapping(value = "/moduleURL")
    public void redirecting(@RequestParam("moduleURL") String url, HttpServletResponse resp, OAuth2Authentication p) throws IOException{
    	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) p.getDetails();
    	String token = details.getTokenValue();
    	Cookie cookie = new Cookie("token", token);
    	cookie.setMaxAge(60*5);
    	resp.addCookie(cookie);
        resp.sendRedirect(url);
    }
    
    
    @RequestMapping(value = "/moduleregistration", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String> postModuleRegistration(@RequestBody ModuleRegDTO data){
    	Map<String,String> status = new HashMap<>();
    	
    	//calls service layer insert for new permissions
    	ps.insertPermissionByName(data);

		status.put("msg", "Module registration was successful.");
        return status;
     
    	//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(status);
    	//return ResponseEntity.ok(json);
    }
}
