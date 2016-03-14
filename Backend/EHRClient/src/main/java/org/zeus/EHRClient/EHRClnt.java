package org.zeus.EHRClient;

import sun.misc.BASE64Encoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class EHRClnt {

	public static void main(String a[]){

		String url = "http://localhost:8080/HealthEnhancements/webresources/UserAuthentication";
		String name = "namit";
		String password = "gupta";
		String authString = name + ":" + password;
		String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
		System.out.println("Base64 encoded auth string: " + authStringEnc);
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json")
				.header("authentication", "Basic " + authStringEnc)
				.get(ClientResponse.class);
		if(resp.getStatus() != 200){
			System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: "+output);
	}
}