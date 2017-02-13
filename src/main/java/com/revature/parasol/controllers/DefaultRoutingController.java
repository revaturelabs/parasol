package com.revature.parasol.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class DefaultRoutingController {
    @RequestMapping(value = {"/moduleregistration", "/index"} )
    public String routeToHome(){
        return "forward:index.html";
    }
}
