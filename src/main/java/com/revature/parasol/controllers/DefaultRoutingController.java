package com.revature.parasol.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class DefaultRoutingController {
    @RequestMapping(value = {"/", "/otherVariousURLs", "/login"} )
    public String routeToHome(){
        return "forward:index.html";
    }
}
