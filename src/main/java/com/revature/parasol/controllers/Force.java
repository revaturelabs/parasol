/**
 * 
 */
package com.revature.parasol.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

/**
 * @author Marc
 *
 */

@Component
public class Force {

    private static final String REST_VERSION = "35.0";

    @Bean
    private OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource,
	    OAuth2ClientContext context) {
    	return new OAuth2RestTemplate(resource, context);
    }

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    private static String restUrl(OAuth2Authentication principal) {
    	HashMap<String, Object> details = (HashMap<String, Object>) principal.getUserAuthentication().getDetails();
		HashMap<String, String> urls = (HashMap<String, String>) details.get("urls");
		return urls.get("rest").replace("{version}", REST_VERSION);
    }

    //ADDED Billy Code
    @SuppressWarnings("unchecked")
    private static String getUserId(OAuth2Authentication principal) {
        //Maps the Force.com Identity Service (user info)
        HashMap<String, Object> details = (HashMap<String, Object>) principal.getUserAuthentication().getDetails();
        String userId = (String) details.get("user_id");
        return  userId;
    }

    //Gets the user role from Salesforce
    public String getRoleName(OAuth2Authentication principal) {
        String url = restUrl(principal) + "query/?q={q}";
        String userId = getUserId(principal);

        //String query
        Map<String, String> params = new HashMap<>();
        params.put("q", "SELECT Name FROM userRole WHERE Id in " +
        "(SELECT userroleid FROM user where id='" + userId +"')");

        String role = null;

        JSONObject response;
        try {
            response = new JSONObject(restTemplate.getForObject(url, String.class, params));
            role = response.getJSONArray("records").getJSONObject(0).getString("Name");
        } catch (RestClientException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return role;
    }

    //Gets all users from SalesForce
    public String getAllUsers(OAuth2Authentication principal){
        String url = restUrl(principal) + "query/?q={q}";
        String userId = getUserId(principal);
        JSONObject response = null;

        //String query
        Map<String, String> params = new HashMap<>();

        //gets ALL users
        params.put("q", "SELECT Name FROM user where id = '" + userId + "'");
        //params.put("q", "SELECT Name FROM user");
        String test = null;

        try {
            response = new JSONObject(restTemplate.getForObject(url, String.class, params));
            test =  response.getJSONArray("records").getJSONObject(0).getString("Name");

        } catch (RestClientException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return test;
    }

    //Checks whether the user role is an admin or not
    public static boolean isAdmin(String role) {
        if (role.equals("VP of Technology") || role.equals("COO")||
            role.equals("CFO") || role.equals("CMO")||
            role.equals("Content and Quality") || role.equals("Trainers")) {
            return true;
        }else {
            return false;
        }
    }
}
