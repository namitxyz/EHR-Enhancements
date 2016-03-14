package org.zeus.HealthEnhancements;

import java.io.IOException;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class UserInfo {

	public UserInfo()
	{
	}

	public boolean IsValidUser(String authString)
	{
		//query the database to check if this string exists in the database or not.
		return true;
	}
	
	public boolean CreateUser(String authString)
	{
		String Decodedauth = GetDecodedAuth(authString);
		
		String[] parts = Decodedauth.split(":");
		
		String UserID = parts[0];
		
		// Add authString and UserID to a table in the database containing encoded IDs and Passwords. Make sure both are unique. If not, return false
		return true; // if success (else false)
	}
	
	public boolean IsProviderPrivilegedToViewPatientData(String szProviderID, String szPatientID)
	{
		// query the database to check if the provider can view patient data or not
		return true;
	}
	
	public boolean AddPatientToProviderMapping(String szPatientID, String szProviderID) 
	{
		// Add a mapping from patient to possible providers who can view their records
		return true; //if success (else false)
	}
	
	private String GetDecodedAuth(final String authString)
	{
		String decodedAuth = "";
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
		return decodedAuth;	
	}

}
