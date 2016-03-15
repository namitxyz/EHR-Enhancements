package org.zeus.HealthEnhancements;

public class ProviderInfo {

	String m_szUserName = "";
	String m_szHashUserPassword = "";
	String m_szProviderType = "";
	String m_szFirstName = "";
	String m_szLastName = "";
	String m_szMiddleName = "";
	String m_szSocialSecurity = "";
	String m_szLicenseNumber = "";
	String m_szPersonnelType = "";
	String m_szSpecialization = "";
	String m_szAffiliation = "";
	String m_szAddress = "";
	String m_szPhoneNumber = "";
	String m_szEmail = "";

	public ProviderInfo()
	{

	}

	public boolean CreateProviderProfile() 
	{
		//add a record in the provider table with all the data
		return true;  //successfully created provider profile (else false)
	}

	public void LoadProviderProfile(String authString) 
	{
		//m_szUserName = GetDecodedAuth(authString);
		m_szHashUserPassword = authString;

		// query the database for the user data and load the member variables
	}

	public boolean UpdateProviderProfile() 
	{
		//update a record in the provider table with all the data
		return true; //successfully updated patient profile (else false)
	}

}
