/**
 * 
 */
package com.revature.parasol.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.revature.parasol.service.LoginHelperService;
/**
 * @author Marc
 *
 */
@RestController
public class LoginController {

	LoginHelperService lhs = new LoginHelperService();
	
    @RequestMapping("/authenticate")
    public void loginUser(@RequestParam(value = "username") String username,
	    @RequestParam(value = "password") String password) {

	// Get an HttpClient for sending the request
	HttpClient httpclient = HttpClients.createDefault();
	
	//Get information for the request
	HashMap<String, String> reqInfo = lhs.getReqInfo(username, password);
	
	// Set up the Post method to send
	HttpPost post = new HttpPost(reqInfo.get("url"));
	
	List<NameValuePair> params = lhs.getValuePairList(reqInfo);
	
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
