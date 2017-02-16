package com.revature.parasol.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.parasol.domain.dto.ModuleRegDTO;
import com.revature.parasol.domain.dto.RolesDTO;
import com.revature.parasol.domain.dto.Status;
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
    public Status postModuleRegistration(@RequestBody ModuleRegDTO data){
        //calls service layer insert for new permissions
        //ps.insertPermissionByName(data);
    	Status status = new Status();
    	status.setStatus("error");
    	status.setError("Module already exist");
        return status;
        
    }
}
