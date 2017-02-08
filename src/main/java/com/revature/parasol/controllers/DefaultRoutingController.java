package com.revature.parasol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class DefaultRoutingController {
    @RequestMapping(value = {"/", "/otherVariousURLs"} )
    public String routeToHome(){
        return "forward:index.html";
    }
}
