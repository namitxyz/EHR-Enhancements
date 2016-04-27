package org.zeus.HealthEnhancements.FHIR;

public class Observation {
	private String m_szPatientID = "";
	private String m_szObservationStatues = "";
	private String m_szObservationCode = "";
	private String m_szObservationDisplay = "";
	private String m_szObservationValue = "";
	private String m_szObservationUnit = "";
	private String m_szObservationEffectiveTime = "";
	private String m_szObservationID = "";
	private String m_szEncounterID = "";
	
	public String getPatientID() {
		return m_szPatientID;
	}
	public void setPatientID(String patientID) {
		m_szPatientID = patientID;
	}
	public String getObservationStatues() {
		return m_szObservationStatues;
	}
	public void setObservationStatues(String observationStatues) {
		m_szObservationStatues = observationStatues;
	}
	public String getObservationCode() {
		return m_szObservationCode;
	}
	public void setObservationCode(String observationCode) {
		m_szObservationCode = observationCode;
	}
	public String getObservationDisplay() {
		return m_szObservationDisplay;
	}
	public void setObservationDisplay(String observationDisplay) {
		m_szObservationDisplay = observationDisplay;
	}
	public String getObservationValue() {
		return m_szObservationValue;
	}
	public void setObservationValue(String observationValue) {
		m_szObservationValue = observationValue;
	}
	public String getObservationUnit() {
		return m_szObservationUnit;
	}
	public void setObservationUnit(String observationUnit) {
		m_szObservationUnit = observationUnit;
	}
	public String getObservationEffectiveTime() {
		return m_szObservationEffectiveTime;
	}
	public void setObservationEffectiveTime(String observationEffectiveTime) {
		m_szObservationEffectiveTime = observationEffectiveTime;
	}
	public String getObservationID() {
		return m_szObservationID;
	}
	public void setObservationID(String observationID) {
		m_szObservationID = observationID;
	}
	public String getEncounterID() {
		return m_szEncounterID;
	}
	public void setEncounterID(String encounterID) {
		m_szEncounterID = encounterID;
	}
}
