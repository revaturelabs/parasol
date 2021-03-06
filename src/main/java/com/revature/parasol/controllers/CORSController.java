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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class CORSController {

	@Autowired
	Force force;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Map<String, String> getAllUsers(OAuth2Authentication p) throws JSONException {
		//holds the data to be returned
		Map<String, String> map = new HashMap<>();

		//calls force to get users from SF
		JSONArray result = force.getAllUsers(p);

		//loading map from result
		map.put("data", result.toString());

		//returns map
		return map;
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public Map<String, String> getAllContacts(OAuth2Authentication p) throws JSONException {
		//holds the data to be returned
		Map<String, String> map = new HashMap<>();

		//calls force to get contacts from SF
		JSONArray result = force.getAllContacts(p);

		//loading map from result
		map.put("data", result.toString());

		//returns map
		return map;
	}
	
	@RequestMapping(value = "/createContact", method = RequestMethod.GET)
	public Map<String, String> createContact(@RequestParam("LastName") String ln, OAuth2Authentication p) {
		//holds the data to be returned
		Map<String, String> map = new HashMap<>();
		System.out.println("THE LAST NAME IS " + ln);
		//Insert contact
		force.insertContact(p, ln);
		
		//map status
		map.put("status", "It might have worked.");

		//returns map
		return map;
	}
}
