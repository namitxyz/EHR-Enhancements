package org.zeus.HealthEnhancements;

public class PatientToProvider {
	
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

}
