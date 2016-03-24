package org.zeus.EHRClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class EHRClnt {

	public static void main(String a[]){

		String name = "namit5";
		String password = "namit5";
		String authString = "{\"m_id\":\"56f0ae9096eaf107fe06ec74\",\"m_szUserName\":\"namit5\",\"m_szHashUserPassword\":\"gupta"
				+ "\",\"m_szFirstName\":\"barack\",\"m_szLastName\":\"obama\",\"m_szMiddleInitial\":\"\",\"m_szAddress\":\"\",\"m_szPhoneNumber"
				+ "\":\"\",\"m_szEmail\":\"\",\"m_szDateOfBirth\":\"\",\"m_szGender\":\"\",\"m_szRace\":\"\","
				+ "\"m_szEthnicity\":\"\",\"m_szPrimaryLanguage\":\"\",\"m_szMartialStatus\":\"\",\"m_szSocialSecurity\":\"\","
				+ "\"m_szEmployer\":\"\",\"m_szEmploymentStatus\":\"\",\"m_szEmergencyFirstName\":\"\",\"m_szEmergencyLastName\":\"\","
				+ "\"m_szEmergencyPhoneNumber\":\"\",\"m_szEmergencyRelationship\":\"\",\"m_szEmergencyAddress\":\"\","
				+ "\"m_szInsuraceCompanyName\":\"\",\"m_szInsurancePhoneNumber\":\"\",\"m_szInsuranceSubsriberID\":\"\","
				+ "\"m_szInsuranceGroupID\":\"\"}";		

		Client restClient = Client.create();

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

		String url = "http://localhost:8080/HealthEnhancements/webresources/GetAppointment";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
				.header("PatientID", "namit5")
				.header("ProviderID", "namit5")
				.get(ClientResponse.class);

		if(resp.getStatus() != 200){
			System.err.println("Unable to connect to the server");
		}

		String output = resp.getEntity(String.class);
		System.out.println("response: "+output);
	}
}
