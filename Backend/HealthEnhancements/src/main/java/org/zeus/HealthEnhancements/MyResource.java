
package org.zeus.HealthEnhancements;

import org.zeus.HealthEnhancements.UserInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import sun.misc.BASE64Decoder;

@SuppressWarnings({ "unused", "restriction" })
@Path("/")
public class MyResource {

	public static UserInfo StringToObject_UserInfo(String p_json) 
	{
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(p_json, UserInfo.class);
	}

	public static ProviderInfo StringToObject_ProviderInfo(String p_json) 
	{
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(p_json, ProviderInfo.class);
	}

	public static String ObjectToString(Object p_obj) 
	{
		Gson gson = new GsonBuilder().create();
		return gson.toJson(p_obj);
	}
	@POST
	@Path ("CreateUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CreateUser(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{
		UserInfo user = new UserInfo();

		if(!user.CreateUser(szUserName, szPassword))
			return "{\"error\":\"User already exists in the system. Please try again with a different username and password\"}";
		else
			return ObjectToString(user);	
	}

	@GET
	@Path("UserLogin")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CheckUserIdAndPassword(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{	
		UserInfo user = new UserInfo();

		if(!user.IsValidUser(szUserName, szPassword))
			return "{\"error\":\"User not authenticated\"}";
		else
			return "{\"Success\":\"User authenticated\"}";
	}

	@GET
	@Path("GetPatientProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object GetPatientProfile(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{
		UserInfo user = new UserInfo();

		try
		{
			user.LoadPatientProfile(szUserName, szPassword);
		}
		catch(Exception e)
		{
			return e.getMessage();

		}
		return ObjectToString(user);
	}

	@PUT
	@Path("UpdatePatientProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object UpdatePatientProfile(@HeaderParam("userInfo") String szUser)
	{
		UserInfo user = StringToObject_UserInfo(szUser);

		try
		{
			if(user.UpdatePatientProfile())
				return "{\"success\":\"Patient profile updated\"}";	
		}
		catch(Exception e)
		{
			return e.getMessage();
		}

		return "{\"error\":\"Patient profile not updated\"}";
	}
	
	@POST
	@Path ("CreateProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CreateProvider(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{
		ProviderInfo provider = new ProviderInfo();

		if(!provider.CreateProvider(szUserName, szPassword))
			return "{\"error\":\"Provider already exists in the system. Please try again with a different username and password\"}";
		else
			return ObjectToString(provider);
	}

	@GET
	@Path("ProviderLogin")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CheckProviderIdAndPassword(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{	
		ProviderInfo provider = new ProviderInfo();

		if(!provider.IsValidUser(szUserName, szPassword))
			return "{\"error\":\"Provider not authenticated\"}";
		else
			return "{\"Success\":\"Provider authenticated\"}";
	}

	@GET
	@Path("GetProviderProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object GetProviderProfile(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{
		ProviderInfo provider = new ProviderInfo();

		provider.LoadProviderProfile(szUserName, szPassword);
		return ObjectToString(provider);
	}

	@PUT
	@Path("UpdateProviderProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object UpdateProviderProfile(@HeaderParam("providerInfo") String szProvider)
	{
		ProviderInfo provider = StringToObject_ProviderInfo(szProvider);

		if(!provider.UpdateProviderProfile())
			return "{\"error\":\"Provider profile not updated\"}";
		else
			return "{\"success\":\"Provider profile updated\"}";	
	}
	
	@POST
	@Path ("AddPatientToProviderMapping")
	@Produces(MediaType.APPLICATION_JSON)
	public Object AddPatientToProviderMapping(@HeaderParam("PatientID") String szPatientID, 
			@HeaderParam("ProviderID") String szProviderID)
	{
		PatientToProvider p2p = new PatientToProvider();

		if(!p2p.AddPatientToProviderMapping(szPatientID, szProviderID))
			return "{\"error\":\"Patient-Provider mapping not added to the system\"}";
		else
			return "{\"success\":\"Patient-Provider mapping added to the system\"}";	
	}

	@GET
	@Path("GetPatientToProviderMapping")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CheckPrivilege(@HeaderParam("PatientID") String szPatientID, 
			@HeaderParam("ProviderID") String szProviderID)
	{
		PatientToProvider p2p = new PatientToProvider();

		if(!p2p.IsProviderPrivilegedToViewPatientData(szProviderID, szPatientID))
			return "{\"error\":\"Provider cannot view patient data\"}";
		else
			return "{\"success\":\"Provider can view patient data\"}";		
	}

	@GET
	@Path("GetAllPatientsForProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public Object GetAllPatientsForProvider(@HeaderParam("ProviderID") String szProviderID)
	{
		return "";

	}

	@GET
	@Path("GetAllProvidersForPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public Object GetAllProvidersForPatient(@HeaderParam("PatientID") String szPatientID)
	{
		return "";

	}

	@POST
	@Path("AddFeedbackForProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public Object AddFeedbackForProvider(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID, @HeaderParam("feedback") String szfeedback)
	{
		return "";

	}

	@GET
	@Path("ViewAllFeedbackForProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public Object ViewAllFeedbackForProvider(@HeaderParam("ProviderID") String szProviderID)
	{
		return "";

	}

	@GET
	@Path("ViewAllFeedbackGivenByPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public Object ViewAllFeedbackGivenByPatient(@HeaderParam("PatientID") String szPatientID)
	{
		return "";

	}

	@GET
	@Path("ViewAllFeedbackGivenByPatientForProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public Object ViewAllFeedbackGivenByPatientForProvider(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID)
	{
		return "";

	}

	@POST
	@Path("AddMedicalRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public Object AddMedicalRecord(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID, @HeaderParam("MedicalRecordJSON") String szMedicalRecord)
	{
		//also store the timestamp
		return "";

	}

	@GET
	@Path("ViewEntireMedicalHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public Object ViewEntireMedicalHistory(@HeaderParam("PatientID") String szPatientID)
	{
		return "";

	}

	@GET
	@Path("ViewLatestMedicalHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public Object ViewLatestMedicalHistory(@HeaderParam("PatientID") String szPatientID)
	{
		return "";

	}

	@POST
	@Path("AddAppointment")
	@Produces(MediaType.APPLICATION_JSON)
	public Object AddAppointment(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID, @HeaderParam("DateTime") String szDateTime)
	{
		return "";

	}

	@GET
	@Path("GetAppointment")
	@Produces(MediaType.APPLICATION_JSON)
	public Object DeleteAppointment(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID)
	{
		return "";

	}

	@PUT
	@Path("UpdateAppointment")
	@Produces(MediaType.APPLICATION_JSON)
	public Object UpdateAppointment(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID, @HeaderParam("DateTime") String szDateTime)
	{
		return "";

	}

	@DELETE
	@Path("DeleteAppointment")
	@Produces(MediaType.APPLICATION_JSON)
	public Object DeleteAppointment(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID, @HeaderParam("DateTime") String szDateTime)
	{
		return "";

	}

	@POST
	@Path("SendEmailToAnotherProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public Object SendEmailToAnotherProvider(@HeaderParam("PatientID") String szPatientID, @HeaderParam("OrigProviderID") String szOrigProviderID, @HeaderParam("NewProviderID") String szNewProviderID, @HeaderParam("EmailContent") String szEmailContent)
	{
		return "";

	}
}