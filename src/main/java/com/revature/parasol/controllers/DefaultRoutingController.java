package com.revature.parasol.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class DefaultRoutingController {
    @RequestMapping(value = "/")
    public String routeToHome(){
        return "forward:index.html";
    }
    
    @RequestMapping(value="/moduleRegistration", method=RequestMethod.POST)
    public String modTest(){
    	
    	
    	
    	return "hi";
    }
}
