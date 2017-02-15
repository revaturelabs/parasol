/**
 * 
 */
package com.revature.parasol.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

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

        String role = restTemplate.getForObject(url, String.class, params);
        System.out.println(role);
        return role;
    }
   //ADDED Billy Code

    public String printRestUrl(OAuth2Authentication principal)
    {
	return restUrl(principal);
    }
}
