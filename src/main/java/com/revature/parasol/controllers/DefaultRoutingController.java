package com.revature.parasol.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String redirecting(@RequestParam("moduleURL") String url, HttpServletResponse resp){
    	Cookie cookie = new Cookie("hi", "world");
    	resp.addCookie(cookie);
        return "redirect:" + url;
    }
    
    
    @RequestMapping(value = "/moduleregistration", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,String> postModuleRegistration(@RequestBody ModuleRegDTO data){
    	Map<String,String> status = new HashMap<>();
    	
    	//calls service layer insert for new permissions
    	try {
        	ps.insertPermissionByName(data);
        	//Returns nothing really
        	return status;
    	} catch(HibernateException e) {
            //Response or status
    		status.put("error", "Module Already Exist");
            return status;
    	}
        
    	//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(status);
    	//return ResponseEntity.ok(json);
    }
}
