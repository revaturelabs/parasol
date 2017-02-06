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

   
    public String printRestUrl(OAuth2Authentication principal)
    {
	return restUrl(principal);
    }
}
