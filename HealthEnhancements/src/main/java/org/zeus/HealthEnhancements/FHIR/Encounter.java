package org.zeus.HealthEnhancements.FHIR;

public class Encounter {
	private String m_szPatientID = "";
	private String m_szEncounterID = "";
	private String m_szEncounterTime = "";
	private String m_szProviderOrganizationID = "";
	private String m_szEncounterClass = "";
	private String m_szLocationID = "";
	private String m_szEpisodeofCareID = "";
	
	public String getPatientID() {
		return m_szPatientID;
	}
	public void setPatientID(String patientID) {
		m_szPatientID = patientID;
	}
	public String getEncounterID() {
		return m_szEncounterID;
	}
	public void setEncounterID(String encounterID) {
		m_szEncounterID = encounterID;
	}
	public String getEncounterTime() {
		return m_szEncounterTime;
	}
	public void setEncounterTime(String encounterTime) {
		m_szEncounterTime = encounterTime;
	}
	public String getProviderOrganizationID() {
		return m_szProviderOrganizationID;
	}
	public void setProviderOrganizationID(String providerOrganizationID) {
		m_szProviderOrganizationID = providerOrganizationID;
	}
	public String getEncounterClass() {
		return m_szEncounterClass;
	}
	public void setEncounterClass(String encounterClass) {
		m_szEncounterClass = encounterClass;
	}
	public String getLocationID() {
		return m_szLocationID;
	}
	public void setLocationID(String locationID) {
		m_szLocationID = locationID;
	}
	public String getEpisodeofCareID() {
		return m_szEpisodeofCareID;
	}
	public void setEpisodeofCareID(String episodeofCareID) {
		m_szEpisodeofCareID = episodeofCareID;
	}
}
