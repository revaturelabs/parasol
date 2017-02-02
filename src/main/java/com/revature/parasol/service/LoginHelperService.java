package com.revature.parasol.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

public class LoginHelperService {
	
	public HashMap getReqInfo(String username, String password){
		
		HashMap<String, String> reqInfo = new HashMap();
		
		// Build the strings that will make up the request
		String url = "https://test.salesforce.com/services/oauth2/token";
		String grantType = "password";
		String clientId = "";
		String clientSecret = "";

		String message = "grant_type=" + grantType + "&client_id" + clientId + "&client_secret" + clientSecret
			+ "&username" + username + "&password" + password;

		String finalUrl = url + "?" + message;
		
		reqInfo.put("url", url);
		reqInfo.put("username", username);
		reqInfo.put("password", password);
		reqInfo.put("grandType", grantType);
		reqInfo.put("clientId", clientId);
		reqInfo.put("clientSecret", clientSecret);
		reqInfo.put("message", message);
		reqInfo.put("finalUrl", finalUrl);
		
		return reqInfo;
	}
	
	public List<NameValuePair> getValuePairList (HashMap<String, String> info){
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type", info.get("grantType")));
		params.add(new BasicNameValuePair("client_id", info.get("clientId")));
		params.add(new BasicNameValuePair("client_secret", info.get("clientSecret")));
		params.add(new BasicNameValuePair("username", info.get("username")));
		params.add(new BasicNameValuePair("password",info.get("password")));
		
		return params;
	}
}
