package com.revature.parasol.controllers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("ACCESS_TOKEN")
public class DefaultRoutingController {
	private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	private static final String INSTANCE_URL = "INSTANCE_URL";

	// clientId is 'Consumer Key' in the Remote Access UI
	private static String clientId = System.getenv("SF_ID");
	// clientSecret is 'Consumer Secret' in the Remote Access UI
	private static String clientSecret = System.getenv("SF_SECRET");
	// This must be identical to 'Callback URL' in the Remote Access UI
	private static String redirectUri = "https://localhost:8080/auth/salesforce/callback";
	private static String SF_URL = System.getenv("SF_URL");
	private String authUrl = null;
	private String tokenUrl = null;
	
    @RequestMapping(value = {"/", "/home"} )
    public String routeToHome(){
        //return "forward:index.html";
    	return "index.html";
    }
    
    @RequestMapping(value = "/login")
    public String routeToLogin() {
        return "logins.html";
    }
    
    @RequestMapping(value = "/auth/salesforce")
	public String authenticate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
    	//Grab token
		String accessToken = (String) request.getSession().getAttribute(ACCESS_TOKEN);
		//If the token exist, redirect to the salesforce login page
		if(accessToken == null) {
			try {
				authUrl = SF_URL
						+ "/services/oauth2/authorize?response_type=code&client_id="
						+ clientId + "&redirect_uri="
						+ URLEncoder.encode(redirectUri, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new ServletException(e);
			}
			return "redirect:" + authUrl;
		} else {
			return "redirect:/auth/salesforce/callback";
		}
	}
    
    @RequestMapping(value = "/auth/salesforce/callback")
	@ResponseBody
	public String authenticateCallback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Grab token
    	String accessToken = (String) request.getSession().getAttribute(ACCESS_TOKEN);
		//If token doesn't exist
		if(accessToken == null) {
			String instanceUrl = null;
			
			tokenUrl = SF_URL + "/services/oauth2/token";
			
			String code = request.getParameter("code");

			HttpClient httpclient = new HttpClient();

			PostMethod post = new PostMethod(tokenUrl);
			post.addParameter("code", code);
			post.addParameter("grant_type", "authorization_code");
			post.addParameter("client_id", clientId);
			post.addParameter("client_secret", clientSecret);
			post.addParameter("redirect_uri", redirectUri);

			try {
				//Execute post method to the salesforce token page
				httpclient.executeMethod(post);
				try {
					//Read post response
					BufferedReader br = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
					String resp = (br.readLine());
					//Convert response string to a json object
					JSONObject authResponse = new JSONObject(new JSONTokener(resp));
					
					//Test Prints
					System.out.println("Post reponse:" + resp);
					System.out.println("Auth response: " + authResponse.toString());

//					accessToken = authResponse.getString("access_token");
//					instanceUrl = authResponse.getString("instance_url");
//					accessToken = authResponse.getString("error_description");
//					instanceUrl = authResponse.getString("error");

				} catch (JSONException e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			} finally {
				post.releaseConnection();
			}
			// Set a session attribute so that other servlets can get the access
			// token
			request.getSession().setAttribute(ACCESS_TOKEN, accessToken);
			
			// We also get the instance URL from the OAuth response, so set it
			// in the session too
			request.getSession().setAttribute(INSTANCE_URL, instanceUrl);
		}

		return "CallBack Thing Worked! <a href=\"/logout\">Log out</a> | "
				+ "Got Error: <b>" + "HI" + "</b>";
	}
    
    @RequestMapping(value = "/helloo")
    public String routeToLoginCheck(){
        return "hello.html";
    }
}
