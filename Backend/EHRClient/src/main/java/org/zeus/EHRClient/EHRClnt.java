package org.zeus.EHRClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class EHRClnt {

	public static void main(String a[]){

		String url = "http://localhost:8080/HealthEnhancements/webresources/GetPatientProfile";
		String name = "namit";
		String password = "gupta";
		String authString = name + ":" + password;
		/*String authString = "{\"m_id\":\"56ef107096eac02cbca7f19d\",\"m_szUserName\":\"namit\",\"m_szHashUserPassword\":\"gupta"
				+ "\",\"m_szFirstName\":\"barack\",\"m_szLastName\":\"obama\",\"m_szMiddleInitial\":\"\",\"m_szAddress\":\"\",\"m_szPhoneNumber"
				+ "\":\"\",\"m_szEmail\":\"\",\"m_szDateOfBirth\":\"\",\"m_szGender\":\"\",\"m_szRace\":\"\","
				+ "\"m_szEthnicity\":\"\",\"m_szPrimaryLanguage\":\"\",\"m_szMartialStatus\":\"\",\"m_szSocialSecurity\":\"\","
				+ "\"m_szEmployer\":\"\",\"m_szEmploymentStatus\":\"\",\"m_szEmergencyFirstName\":\"\",\"m_szEmergencyLastName\":\"\","
				+ "\"m_szEmergencyPhoneNumber\":\"\",\"m_szEmergencyRelationship\":\"\",\"m_szEmergencyAddress\":\"\","
				+ "\"m_szInsuraceCompanyName\":\"\",\"m_szInsurancePhoneNumber\":\"\",\"m_szInsuranceSubsriberID\":\"\","
				+ "\"m_szInsuranceGroupID\":\"\"}";*/

		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
				.header("authentication", authString)
				.get(ClientResponse.class);
		
		if(resp.getStatus() != 200){
			System.err.println("Unable to connect to the server");
		}
		
		String output = resp.getEntity(String.class);
		System.out.println("response: "+output);
	}
}