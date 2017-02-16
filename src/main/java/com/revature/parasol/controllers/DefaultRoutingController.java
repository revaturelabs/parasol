package com.revature.parasol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping(value = "/moduleregistration", method=RequestMethod.POST)
    @ResponseBody
    public String postModuleRegistration(@RequestBody ModuleRegDTO data){
    	System.out.println(data.getCheckedData().size());
    	System.out.println(data.getCheckedData().get(0).getValue());
    	System.out.println(data.getModuleName());
              
        //calls service layer insert for new permissions        
        //ps.insertPermissionByName(data);
        return "HI";
        
    }
}
