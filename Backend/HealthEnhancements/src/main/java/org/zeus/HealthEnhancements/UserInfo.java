package org.zeus.HealthEnhancements;

import java.io.IOException;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class UserInfo {

	String m_szUserName = "";
	String m_szHashUserPassword = "";
	String m_szFirstName = "";
	String m_szLastName = "";
	String m_szMiddleInitial = "";
	String m_szAddress = "";
	String m_szPhoneNumber = "";
	String m_szEmail = "";
	String m_szDateOfBirth = "";
	String m_szGender = "";
	String m_szRace = "";
	String m_szEthnicity = "";
	String m_szPrimaryLanguage = "";
	String m_szMartialStatus = "";
	String m_szSocialSecurity = "";
	String m_szEmployer = "";
	String m_szEmploymentStatus = "";
	String m_szEmergencyFirstName = "";
	String m_szEmergencyLastName = "";
	String m_szEmergencyPhoneNumber = "";
	String m_szEmergencyRelationship = "";
	String m_szEmergencyAddress = "";
	String m_szInsuraceCompanyName = "";
	String m_szInsurancePhoneNumber = "";
	String m_szInsuranceSubsriberID = "";
	String m_szInsuranceGroupID = "";

	public UserInfo()
	{
	}

	public boolean IsValidUser(String authString)
	{
		m_szUserName = GetDecodedAuth(authString);
		m_szHashUserPassword = authString;

		//query the database to check if this string exists in the database or not.
		return true;
	}

	public boolean CreateUser(String authString)
	{
		m_szUserName = GetDecodedAuth(authString);
		m_szHashUserPassword = authString;

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

	public boolean CreatePatientProfile() 
	{
		//add a record in the patient table with all the data
		return true;  //successfully created patient profile (else false)
	}

	public void LoadPatientProfile(String authString) 
	{
		m_szUserName = GetDecodedAuth(authString);
		m_szHashUserPassword = authString;

		// query the database for the user data and load the member variables
	}

	public boolean UpdatePatientProfile() 
	{
		//update a record in the patient table with all the data
		return true; //successfully updated patient profile (else false)
	}

	private String GetDecodedAuth(final String authString)
	{
		String decodedAuth = "";
		try{
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

			String[] parts = decodedAuth.split(":");
			return parts[0];
		}
		catch(Exception e)
		{
			return decodedAuth;
		}	
	}

}
