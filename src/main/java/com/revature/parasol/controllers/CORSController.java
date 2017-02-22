package com.revature.parasol.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8085")
@RequestMapping(value="/api")
public class CORSController {

	@Autowired
	Force force;

//	@CrossOrigin(origins = "http://ec2-35-163-220-111.us-west-2.compute.amazonaws.com")
//	@RequestMapping("users/{id}")
//	public Map<String, String> retrieve(@PathVariable Long id) {
//		Map<String, String> map = new HashMap<>();
//		map.put("HI", "Hello World");
//		return map;
//	}


	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Map<String,Object> getAllUsers(HttpServletRequest req, OAuth2Authentication p) throws JSONException {
		System.out.println("Cookies Found " + req.getCookies().length);
		//holds the data to be returned
		Map<String,Object> map = new HashMap<>();

		//calls force to get users from SF
		String result = force.getAllUsers(p);

		//loading map from result
		map.put("data", result);

		//returns map
		return map;
	}
	
}
