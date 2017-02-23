/**
 * 
 */
package com.revature.parasol.controllers;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marc
 *
 */

@Component
public class Force {

    private static final String REST_VERSION = "35.0";
    
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

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
    public JSONArray getAllUsers(OAuth2Authentication principal){
        String url = restUrl(principal) + "query/?q={q}";
        JSONObject response = null;

        //String query
        Map<String, String> params = new HashMap<>();

        //gets ALL users
        //params.put("q", "SELECT Name FROM user where id = '" + userId + "'");
        params.put("q", "SELECT Name FROM user");
        JSONArray test = new JSONArray();

        try {
            response = new JSONObject(restTemplate.getForObject(url, String.class, params));
            int size = response.getInt("totalSize");
            for (int i = 0; i < size; i++) {
            	JSONObject tmp = new JSONObject();
            	tmp.put("name", response.getJSONArray("records").getJSONObject(i).getString("Name"));
                test.put(tmp);
            }

        } catch (RestClientException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return test;
    }
    
    public void insertContact(OAuth2Authentication principal) {
    	  String uri = restUrl(principal) + "sobjects/Account/";
    	  JSONObject test = new JSONObject();
    	  try {
			test.put("Name", "TestingABC123");
			// set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(test.toString(), headers);
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
			System.out.println("THE SOBJECT RESPONSE CALL IS " + response);
    	  } catch (RestClientException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	  }
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
