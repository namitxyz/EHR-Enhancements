
package org.zeus.HealthEnhancements;

import org.zeus.HealthEnhancements.UserInfo;
import org.zeus.HealthEnhancements.Services.NotificationService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;


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
	public void CreateUser(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{
		String returnObj = "";
		UserInfo user = new UserInfo();
		if(!user.CreateUser(szUserName, szPassword))
			returnObj =  "{\"error\":\"User already exists in the system. Please try again with a different username and password\"}";
		else
			returnObj =  ObjectToString(user);
		
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("UserLogin")
	@Produces(MediaType.APPLICATION_JSON)
	public void CheckUserIdAndPassword(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{	
		String returnObj = "";
		UserInfo user = new UserInfo();

		if(!user.IsValidUser(szUserName, szPassword))
			returnObj =  "{\"error\":\"User not authenticated\"}";
		else
			returnObj =  "{\"Success\":\"User authenticated\"}";
		
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("GetPatientProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public void GetPatientProfile(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{
		String returnObj = "";
		
		UserInfo user = new UserInfo();

		try
		{
			user.LoadPatientProfile(szUserName, szPassword);
			returnObj =  ObjectToString(user);
		}
		catch(Exception e)
		{
			returnObj =  e.getMessage();

		}
		
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Path("UpdatePatientProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public void UpdatePatientProfile(@HeaderParam("userInfo") String szUser)
	{
		String returnObj = "";
		
		UserInfo user = StringToObject_UserInfo(szUser);

		try
		{
			if(user.UpdatePatientProfile())
				returnObj =   "{\"success\":\"Patient profile updated\"}";	
		}
		catch(Exception e)
		{
			
			returnObj =   "{\"error\":\"Patient profile not updated: " +  e.getMessage() + "\"}";
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
		
	}

	@POST
	@Path ("CreateProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public void CreateProvider(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{
		String returnObj = "";
		ProviderInfo provider = new ProviderInfo();

		if(!provider.CreateProvider(szUserName, szPassword))
			returnObj =   "{\"error\":\"Provider already exists in the system. Please try again with a different username and password\"}";
		else
			returnObj =   ObjectToString(provider);
		
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("ProviderLogin")
	@Produces(MediaType.APPLICATION_JSON)
	public void CheckProviderIdAndPassword(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{	
		String returnObj = "";
		ProviderInfo provider = new ProviderInfo();

		if(!provider.IsValidUser(szUserName, szPassword))
			returnObj =   "{\"error\":\"Provider not authenticated\"}";
		else
			returnObj =   "{\"Success\":\"Provider authenticated\"}";
		
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("GetProviderProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public void GetProviderProfile(@HeaderParam("UserName") String szUserName, @HeaderParam("Password") String szPassword)
	{
		String returnObj = "";
		ProviderInfo provider = new ProviderInfo();

		provider.LoadProviderProfile(szUserName, szPassword);
		returnObj = ObjectToString(provider);
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Path("UpdateProviderProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public void UpdateProviderProfile(@HeaderParam("providerInfo") String szProvider)
	{
		String returnObj = "";
		ProviderInfo provider = StringToObject_ProviderInfo(szProvider);

		if(!provider.UpdateProviderProfile())
			returnObj =  "{\"error\":\"Provider profile not updated\"}";
		else
			returnObj =  "{\"success\":\"Provider profile updated\"}";	
		
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path ("AddPatientToProviderMapping")
	@Produces(MediaType.APPLICATION_JSON)
	public void AddPatientToProviderMapping(@HeaderParam("PatientID") String szPatientID, 
			@HeaderParam("ProviderID") String szProviderID)
	{
		String returnObj = "";
		PatientToProvider p2p = new PatientToProvider();

		try
		{
			if(!p2p.AddPatientToProviderMapping(szPatientID, szProviderID))
				returnObj =   "{\"error\":\"Patient-Provider mapping not added to the system\"}";
			else
				returnObj =   "{\"success\":\"Patient-Provider mapping added to the system\"}";	
		}
		catch(Exception e)
		{
			returnObj =   e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
		
	}

	@GET
	@Path("IsValidPatientToProviderMapping")
	@Produces(MediaType.APPLICATION_JSON)
	public void IsValidPatientToProviderMapping(@HeaderParam("PatientID") String szPatientID, 
			@HeaderParam("ProviderID") String szProviderID)
	{
		String returnObj = "";
		PatientToProvider p2p = new PatientToProvider();

		if(!p2p.IsProviderPrivilegedToViewPatientData(szProviderID, szPatientID))
			returnObj = "{\"error\":\"Provider cannot view patient data\"}";
		else
			returnObj = "{\"success\":\"Provider can view patient data\"}";
		
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("GetAllProvidersForPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public void GetAllProvidersForPatient(@HeaderParam("PatientID") String szPatientID)
	{
		String returnObj = "";
		PatientToProvider p2p = new PatientToProvider();

		try
		{
			ArrayList<String> providers = p2p.FindAllProviders(szPatientID);
			returnObj = ObjectToString(providers);
		}
		catch(Exception e)
		{
			returnObj = e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("GetAllPatientsForProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public void GetAllPatientsForProvider(@HeaderParam("ProviderID") String szProviderID)
	{
		String returnObj = "";
		PatientToProvider p2p = new PatientToProvider();

		try
		{
			ArrayList<String> patients = p2p.FindAllPatients(szProviderID);
			returnObj = ObjectToString(patients);
		}
		catch(Exception e)
		{
			returnObj = e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("AddFeedbackForProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public void AddFeedbackForProvider(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID, @HeaderParam("Feedback") String szfeedback)
	{
		String returnObj = "";
		ProviderFeedback feedback = new ProviderFeedback();

		try
		{
			if(!feedback.AddPatientToProviderFeedback(szPatientID, szProviderID, szfeedback))
				returnObj = "{\"error\":\"Provider feedback not added to the system\"}";
			else
				returnObj = "{\"success\":\"Provider feedback added to the system\"}";	
		}
		catch(Exception e)
		{
			returnObj = e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("ViewAllFeedbackForProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public void ViewAllFeedbackForProvider(@HeaderParam("ProviderID") String szProviderID)
	{
		String returnObj = "";
		
		ProviderFeedback feedback = new ProviderFeedback();

		try
		{
			ArrayList<ProviderFeedback> patients = feedback.FindAllFeedbackForProvider(szProviderID);
			returnObj = ObjectToString(patients);
		}
		catch(Exception e)
		{
			returnObj = e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("ViewAllFeedbackGivenByPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public void ViewAllFeedbackGivenByPatient(@HeaderParam("PatientID") String szPatientID)
	{
		String returnObj = "";
		
		ProviderFeedback feedback = new ProviderFeedback();

		try
		{
			ArrayList<ProviderFeedback> providers = feedback.FindAllFeedbackForPatient(szPatientID);
			returnObj = ObjectToString(providers);
		}
		catch(Exception e)
		{
			returnObj = e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("ViewAllFeedbackGivenByPatientForProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public void ViewAllFeedbackGivenByPatientForProvider(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID)
	{
		String returnObj = "";
		ProviderFeedback feedback = new ProviderFeedback();

		try
		{
			ArrayList<ProviderFeedback> fdbk = feedback.GetAllFeedbackGivenByPatientForProvider(szPatientID, szProviderID);
			returnObj = ObjectToString(fdbk);
		}
		catch(Exception e)
		{
			returnObj = e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}


	@POST
	@Path("AddAppointment")
	@Produces(MediaType.APPLICATION_JSON)
	public void AddAppointment(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID, @HeaderParam("DateTime") String szDateTime, @HeaderParam("Notes") String szNotes)
	{
		String returnObj = "";
		Appointment appointment = new Appointment();

		try
		{
			if(!appointment.AddPatientToProviderAppointment(szPatientID, szProviderID, szNotes, szDateTime))
				returnObj = "{\"error\":\"Provider appointment not added to the system\"}";
			else
				returnObj = "{\"success\":\"Provider appointment added to the system\"}";	
		}
		catch(Exception e)
		{
			returnObj = e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("GetAppointment")
	@Produces(MediaType.APPLICATION_JSON)
	public void DeleteAppointment(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID)
	{
		String returnObj = "";
		Appointment appointment = new Appointment();

		try
		{
			ArrayList<Appointment> apmt = appointment.GetAllAppointmentsGivenByPatientForProvider(szPatientID, szProviderID);
			returnObj = ObjectToString(apmt);
		}
		catch(Exception e)
		{
			returnObj = e.getMessage();
		}
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("SendEmailToAnotherProvider")
	@Produces(MediaType.APPLICATION_JSON)
	public void SendEmailToAnotherProvider(@HeaderParam("PatientID") String szPatientID, @HeaderParam("OrigProviderID") String szOrigProviderID, @HeaderParam("NewProviderID") String szNewProviderID, @HeaderParam("EmailContent") String szEmailContent)
	{
		String returnObj = "";
		if(!NotificationService.Notify(szOrigProviderID, szNewProviderID, szPatientID, szEmailContent))
			returnObj = "{\"error\":\"There was an error sending the notification\"}";
		else
			returnObj = "{\"success\":\"Notification has been sent successfully\"}";
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();

	}

	@POST
	@Path("AddMedicalRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public void AddMedicalRecord(@HeaderParam("PatientID") String szPatientID, @HeaderParam("ProviderID") String szProviderID, @HeaderParam("MedicalRecordJSON") String szMedicalRecord)
	{
		String returnObj = "";
		//also store the timestamp
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();

	}

	@GET
	@Path("ViewEntireMedicalHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public void ViewEntireMedicalHistory(@HeaderParam("PatientID") String szPatientID)
	{
		String returnObj = "";
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();

	}

	@GET
	@Path("ViewLatestMedicalHistory")
	@Produces(MediaType.APPLICATION_JSON)
	public void ViewLatestMedicalHistory(@HeaderParam("PatientID") String szPatientID)
	{
		String returnObj = "";
		Response response = Response.status(200).
                entity(returnObj).
                header("Access-Control-Allow-Origin", "*").build();

	}
}