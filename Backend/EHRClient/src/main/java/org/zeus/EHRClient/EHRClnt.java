package org.zeus.EHRClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

import au.com.bytecode.opencsv.CSVReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class EHRClnt {

	public static void main(String a[]) throws IOException{

		Client restClient = Client.create();

		CSVReader reader = null;

		reader = new CSVReader(new FileReader("/Users/namit/Desktop/Patient.csv"),',');
		String [] nextLine;
		//Read one line at a time
		while ((nextLine = reader.readNext()) != null) 
		{
			String m_szUserName = nextLine[0];
			String m_szHashUserPassword = nextLine[0];

			String url = "http://ehr-namitgupta.rhcloud.com/webresources/CreateUser";
			WebResource webResource = restClient.resource(url);
			ClientResponse resp = webResource.accept("application/json")
					.header("UserName", m_szUserName)
					.header("Password", m_szHashUserPassword)
					.post(ClientResponse.class);

			if(resp.getStatus() != 200)
			{
				System.err.println("Unable to connect to the server");
			}

			String output = resp.getEntity(String.class);
			System.out.println(output);
			JSONObject jsonObject = new JSONObject(output);

			try
			{
				String m_id = jsonObject.getString("m_id");

				System.out.println(m_id);

				UserInfo user = new UserInfo();

				user.m_id = m_id;
				user.m_szUserName = nextLine[0];
				user.m_szHashUserPassword = nextLine[0];
				user.m_szLastName = nextLine[1];
				user.m_szFirstName = nextLine[2];
				user.m_szMiddleInitial = nextLine[3];
				user.m_szGender = nextLine[4];
				user.m_szDateOfBirth = nextLine[5];
				user.m_szAddress = nextLine[10] + ", " + nextLine[7] + ", " + nextLine[8] + ", " + nextLine[6];

				Gson gson = new GsonBuilder().create();
				String authString = gson.toJson(user);

				url = "http://ehr-namitgupta.rhcloud.com/webresources/UpdatePatientProfile";
				webResource = restClient.resource(url);
				resp = webResource.accept("application/json")
						.header("UserInfo", authString)
						.put(ClientResponse.class);

				if(resp.getStatus() != 200)
				{
					System.err.println("Unable to connect to the server");
				}

				output = resp.getEntity(String.class);

				System.out.println(output);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}

		/*String url = "http://localhost:8080/HealthEnhancements/webresources/GetAllPatientsForProvider";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
				.header("PatientID", name)
				.header("ProviderID", password)
				.get(ClientResponse.class);*/

		/*String url = "http://localhost:8080/HealthEnhancements/webresources/UpdateProviderProfile";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
				.header("ProviderInfo", authString)
				.put(ClientResponse.class);*/

		/*String url = "http://localhost:8080/HealthEnhancements/webresources/GetProviderProfile";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
		.header("UserName", name)
		.header("Password", password)
		.get(ClientResponse.class);*/

		/*String url = "http://localhost:8080/HealthEnhancements/webresources/AddAppointment";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
				.header("PatientID", "namit5")
				.header("ProviderID", "namit3")
				.header("DateTime", "2016-01-01")
				.header("Notes", "note 2")
				.post(ClientResponse.class);*/

		/*String url = "http://ehr-namitgupta.rhcloud.com/webresources/GetPatientProfile";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
				.header("UserName", "3")
				.header("Password", "3")
				.get(ClientResponse.class);

		if(resp.getStatus() != 200){
			System.err.println("Unable to connect to the server");
		}

		String output = resp.getEntity(String.class);
		System.out.println("response: "+output);*/
	}
}
