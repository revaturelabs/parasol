/**
 * 
 */
package com.revature.parasol.domain.service;

import java.util.List;

/**
 * @author Marc
 *
 */
public interface RoleModuleServiceInterface {
    
    /**
     * Gets the role of a specific user. The user is a user stored in Salesforce,
     * and their role will be retrieved from Salesforce, checked against our database, and 
     * the role stored in the database will be returned to us. 
     * <br> <br> 
     * Steps to take:
     * <br>
     * 1. Get user's role role from Salesforce. The URL passed to this method will be used
     * for this, as will the token.
     * <br> 
     * 2. Check the user's SF role (obtained in 1) against the database. 
     * This will get the final role for the user. It will be a role object.
     * <br>
     * 3. Get the role string from the role object obtained in 2, and return it.
     * <br> <br>
     * @param salesforceUserProfileLink
     * @param token
     * @return
     */
    public String getRoleForUser(String salesforceUserProfileLink, String token);
    
    /**
     * Gets a list of modules that are allowed to be accessed by the role passed.
     * <br> <br>
     * Steps to take:
     * <br> 
     * 1. Get the list of Module objects for the role passed
     * <br>
     * 2. Create a list of Strings. I can only pass strings to the frontend, not objects.
     * <br>
     * 3. Populate the list of strings with the URLs of each module, and return it.
     * <br> <br>
     * @param role
     * @return
     */
    public List<String> getModulesForRole(String role);
}
