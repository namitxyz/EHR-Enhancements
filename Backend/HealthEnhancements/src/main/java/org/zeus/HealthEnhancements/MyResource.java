
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
	public static Object StringToObject(String p_json) 
	{
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(p_json, Object.class);
	}

	public static String ObjectToString(Object p_obj) 
	{
		Gson gson = new GsonBuilder().create();
		return gson.toJson(p_obj);
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

	@GET
	@Path("GetPatientToProviderMapping")
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
	@Path("CreatePatientProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CreatePatientProfile(@HeaderParam("userInfo") final String szUser)
	{

		UserInfo user = (UserInfo) StringToObject(szUser);

		if(!user.CreatePatientProfile())
			return "{\"error\":\"Patient profile not created\"}";
		else
			return "{\"success\":\"Patient profile created\"}";	
	}

	@GET
	@Path("GetPatientProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object GetPatientProfile(@HeaderParam("authentication") String authString)
	{
		UserInfo user = new UserInfo();

		user.LoadPatientProfile(authString);
		return ObjectToString(user);
	}

	@PUT
	@Path("UpdatePatientProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object UpdatePatientProfile(@HeaderParam("userInfo") String szUser)
	{
		UserInfo user = (UserInfo) StringToObject(szUser);

		if(!user.UpdatePatientProfile())
			return "{\"error\":\"Patient profile not updated\"}";
		else
			return "{\"success\":\"Patient profile updated\"}";	
	}

	@POST
	@Path("CreateProviderProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object CreateProviderProfile(@HeaderParam("providerInfo") final String szProvider)
	{

		ProviderInfo provider = (ProviderInfo) StringToObject(szProvider);

		if(!provider.CreateProviderProfile())
			return "{\"error\":\"Provider profile not created\"}";
		else
			return "{\"success\":\"Provider profile created\"}";	
	}

	@GET
	@Path("GetProviderProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object GetProviderProfile(@HeaderParam("authentication") String authString)
	{
		ProviderInfo provider = new ProviderInfo();

		provider.LoadProviderProfile(authString);
		return ObjectToString(provider);
	}

	@PUT
	@Path("UpdateProviderProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object UpdateProviderProfile(@HeaderParam("providerInfo") String szProvider)
	{
		ProviderInfo provider = (ProviderInfo) StringToObject(szProvider);

		if(!provider.UpdateProviderProfile())
			return "{\"error\":\"Provider profile not updated\"}";
		else
			return "{\"success\":\"Provider profile updated\"}";	
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
