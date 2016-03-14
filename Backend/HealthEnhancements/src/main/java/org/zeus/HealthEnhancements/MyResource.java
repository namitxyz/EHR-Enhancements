
package org.zeus.HealthEnhancements;

import org.zeus.HealthEnhancements.UserInfo;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import sun.misc.BASE64Decoder;

@SuppressWarnings({ "unused", "restriction" })
@Path("/")
public class MyResource {
	@GET
	@Path("UserAuthentication")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CheckUserIdAndPassword(@HeaderParam("authentication") String authString)
	{	
		UserInfo user = new UserInfo();

		if(!user.IsValidUser(authString))
			return "{\"error\":\"User not authenticated\"}";
		else
			return "{\"Success\":\"User authenticated\"}";
	}

	@GET
	@Path("UserPrivilege")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CheckPrivilege(@HeaderParam("PatientID") String szPatientID, 
			@HeaderParam("ProviderID") String szProviderID)
	{
		UserInfo user = new UserInfo();

		if(!user.IsProviderPrivilegedToViewPatientData(szProviderID, szPatientID))
			return "{\"error\":\"Provider cannot view patient data\"}";
		else
			return "{\"success\":\"Provider can view patient data\"}";		
	}

	@POST
	@Path ("CreateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CreateUser(@HeaderParam("authentication") String authString)
	{
		UserInfo user = new UserInfo();

		if(!user.CreateUser(authString))
			return "{\"error\":\"User not added to the system\"}";
		else
			return "{\"success\":\"User added to the system\"}";	
	}

	@POST
	@Path ("AddPatientToProviderMapping")
	@Produces(MediaType.APPLICATION_JSON)
	public Object AddPatientToProviderMapping(@HeaderParam("PatientID") String szPatientID, 
			@HeaderParam("ProviderID") String szProviderID)
	{
		UserInfo user = new UserInfo();

		if(!user.AddPatientToProviderMapping(szPatientID, szProviderID))
			return "{\"error\":\"Patient-Provider mapping not added to the system\"}";
		else
			return "{\"success\":\"Patient-Provider mapping added to the system\"}";	
	}


}