/**
 * 
 */
package com.revature.parasol.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marc
 *
 */
@RestController
public class LoginController {

    @RequestMapping("/authenticate")
    public void loginUser(@RequestParam(value = "username") String username,
	    @RequestParam(value = "password") String password) {

	// Get an HttpClient for sending the request
	HttpClient httpclient = HttpClients.createDefault();

	// Build the strings that will make up the request
	String url = "https://test.salesforce.com/services/oauth2/token";
	String grantType = "password";
	String clientId = "";
	String clientSecret = "";

	String message = "grant_type=" + grantType + "&client_id" + clientId + "&client_secret" + clientSecret
		+ "&username" + username + "&password" + password;

	String finalUrl = url + "?" + message;

	// Set up the Post method to send
	HttpPost post = new HttpPost(url);

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair("grant_type", grantType));
	params.add(new BasicNameValuePair("client_id", clientId));
	params.add(new BasicNameValuePair("client_secret", clientSecret));
	params.add(new BasicNameValuePair("username", username));
	params.add(new BasicNameValuePair("password", password));

	try {
	    post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    Logger.getRootLogger().error(e);
	}

	//Get the response and parse it to json
	HttpResponse resp = null;
	String jsonString="";
	try {
	    resp = httpclient.execute(post);
	    if (resp != null) {
		HttpEntity entity = resp.getEntity();
		jsonString = EntityUtils.toString(entity);
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    Logger.getRootLogger().error(e);
	}
	
	//Get and parse the json object from the response
	JSONObject json;
	try {
	    json = new JSONObject(jsonString);
	    json.get("nameOfElement");
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
