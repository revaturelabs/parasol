package com.revature.parasol.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8085")
@RequestMapping(value="/api")
public class CORSController {

	@Autowired
	Force force;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Map<String, Object> getAllUsers(HttpServletRequest req, OAuth2Authentication p) throws JSONException {
//		if (req.getCookies() != null) {
//			System.out.println("Cookies Found " + req.getCookies().length);
//		}

		//holds the data to be returned
		Map<String,Object> map = new HashMap<>();

		//calls force to get users from SF
		JSONArray result = force.getAllUsers(p);
        System.out.println("THE RESULTS " + result);
		//loading map from result
		map.put("data", result);

		//returns map
		return map;
	}
	
}
