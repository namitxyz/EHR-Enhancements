
package org.zeus.HealthEnhancements;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import sun.misc.BASE64Decoder;

@Path("/")
public class MyResource {
	@GET
	@Path("UserAuthentication/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getUserById(@PathParam("userId") Integer userId,
			@HeaderParam("authorization") String authString){

		System.out.println(authString);
		String decodedAuth = "";

		if(!isUserAuthenticated(authString, decodedAuth)){
			return "{\"error\":\"User not authenticated\"}";
		}

		return decodedAuth;
	}

	private boolean isUserAuthenticated(String authString, String decodedAuth){

		//String decodedAuth = "";
		// Header is in the format "Basic 5tyc0uiDat4"
		// We need to extract data before decoding it back to original string
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];
		// Decode the data back to original string
		byte[] bytes = null;
		try {
			bytes = new BASE64Decoder().decodeBuffer(authInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		decodedAuth = new String(bytes);

		// your validation code goes here....

		return true;
	}

}