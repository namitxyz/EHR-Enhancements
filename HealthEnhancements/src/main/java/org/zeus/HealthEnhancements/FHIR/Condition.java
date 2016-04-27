package org.zeus.HealthEnhancements.FHIR;

public class Condition {
	private String m_szPatientID = "";
	private String m_szConditionCode = "";
	private String m_szConditionDisplay = "";
	private String m_szSeverityCode = "";
	private String m_szSeverityDisplay = "";
	private String m_szNotes = "";
	private String m_szPhysicianID = "";
	private String m_szConditionID = "";
	private String m_szEncounterID = "";
	private String m_szOnsetTime = "";
	private String m_szVerificationStatus = "";
	
	public String getPatientID() {
		return m_szPatientID;
	}
	public void setPatientID(String patientID) {
		m_szPatientID = patientID;
	}
	public String getConditionCode() {
		return m_szConditionCode;
	}
	public void setConditionCode(String conditionCode) {
		m_szConditionCode = conditionCode;
	}
	public String getConditionDisplay() {
		return m_szConditionDisplay;
	}
	public void setConditionDisplay(String conditionDisplay) {
		m_szConditionDisplay = conditionDisplay;
	}
	public String getSeverityCode() {
		return m_szSeverityCode;
	}
	public void setSeverityCode(String severityCode) {
		m_szSeverityCode = severityCode;
	}
	public String getSeverityDisplay() {
		return m_szSeverityDisplay;
	}
	public void setSeverityDisplay(String severityDisplay) {
		m_szSeverityDisplay = severityDisplay;
	}
	public String getNotes() {
		return m_szNotes;
	}
	public void setNotes(String notes) {
		m_szNotes = notes;
	}
	public String getPhysicianID() {
		return m_szPhysicianID;
	}
	public void setPhysicianID(String physicianID) {
		m_szPhysicianID = physicianID;
	}
	public String getConditionID() {
		return m_szConditionID;
	}
	public void setConditionID(String conditionID) {
		m_szConditionID = conditionID;
	}
	public String getEncounterID() {
		return m_szEncounterID;
	}
	public void setEncounterID(String encounterID) {
		m_szEncounterID = encounterID;
	}
	public String getOnsetTime() {
		return m_szOnsetTime;
	}
	public void setOnsetTime(String onsetTime) {
		m_szOnsetTime = onsetTime;
	}
	public String getVerificationStatus() {
		return m_szVerificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		m_szVerificationStatus = verificationStatus;
	}
}
