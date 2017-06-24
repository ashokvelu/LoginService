package org.common.login.LoginService.Resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.common.login.LoginService.Model.LoginDAO;
import org.common.login.LoginService.Services.MongoClass;

@Path("login")
public class LoginResource {
	
	MongoClass mongo = new MongoClass();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LoginDAO> login() {
    	return mongo.getAllMessages();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String verifyLogin(LoginDAO login) {
    	
    	String loginStatus = login.getLoginstatus();
    	if (loginStatus.equalsIgnoreCase("YES"))
    	{
    		return mongo.verifyLogin(login);
    	}
    	else if(loginStatus.equalsIgnoreCase("NO"))
    	{
    		return mongo.updateLogoff(login);
    	}
    	else
    		return "Please enter loginstatus";
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String signUp(LoginDAO login){
    	return mongo.insertLogin(login);
    }

}
