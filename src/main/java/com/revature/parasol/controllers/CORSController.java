package com.revature.parasol.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CORSController {

	@CrossOrigin(origins = "http://ec2-35-163-220-111.us-west-2.compute.amazonaws.com")
	@RequestMapping("users/{id}")
	public Map<String, String> retrieve(@PathVariable Long id) {
		Map<String, String> map = new HashMap<>();
		map.put("HI", "Hello World");
		return map;
	}
	
}
