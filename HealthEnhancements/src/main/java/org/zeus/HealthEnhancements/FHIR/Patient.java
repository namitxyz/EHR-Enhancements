package org.zeus.HealthEnhancements.FHIR;

import java.util.ArrayList;
import java.util.List;

public class Patient {
	private String m_szPatientID = "";
	private String m_szLastName = "";
	private String m_szFirstName = "";
	private String m_szMiddleInitial = "";
	private String m_szGender = "";
	private String m_szBirthDate = "";
	private String m_szAddress = "";
	private String m_szZipCode = "";
	private String m_szCity = "";
	private String m_szState = "";
	private String m_szUse = "";
	private List<Observation> m_szObservations = new ArrayList<Observation>();
	private List<Encounter> encounters = new ArrayList<Encounter>();
	private List<Condition> conditons = new ArrayList<Condition>();
	private List<MedicationDispense> medicationDispense = new ArrayList<MedicationDispense>();
	private List<MedicationOrder> medicationOrder = new ArrayList<MedicationOrder>();
	
	public String getPatientID() {
		return m_szPatientID;
	}
	public void setPatientID(String patientID) {
		m_szPatientID = patientID;
	}
	public String getLast_Name() {
		return m_szLastName;
	}
	public void setLast_Name(String last_Name) {
		m_szLastName = last_Name;
	}
	public String getFirstName() {
		return m_szFirstName;
	}
	public void setFirstName(String firstName) {
		m_szFirstName = firstName;
	}
	public String getMiddleInitial() {
		return m_szMiddleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		m_szMiddleInitial = middleInitial;
	}
	public String getGender() {
		return m_szGender;
	}
	public void setGender(String gender) {
		m_szGender = gender;
	}
	public String getBirthDate() {
		return m_szBirthDate;
	}
	public void setBirthDate(String birthDate) {
		m_szBirthDate = birthDate;
	}
	public String getAddress() {
		return m_szAddress;
	}
	public void setAddress(String address) {
		m_szAddress = address;
	}
	public String getZipCode() {
		return m_szZipCode;
	}
	public void setZipCode(String zipCode) {
		m_szZipCode = zipCode;
	}
	public String getCity() {
		return m_szCity;
	}
	public void setCity(String city) {
		m_szCity = city;
	}
	public String getState() {
		return m_szState;
	}
	public void setState(String state) {
		m_szState = state;
	}
	public String getUse() {
		return m_szUse;
	}
	public void setUse(String use) {
		m_szUse = use;
	}
	
	public List<Observation> getObservations() {
		return m_szObservations;
	}
	public List<Encounter> getEncounters() {
		return encounters;
	}
	public List<Condition> getConditions() {
		return conditons;
	}
	public List<MedicationDispense> getMedicationDispense() {
		return medicationDispense;
	}
	public List<MedicationOrder> getMedicationOrder() {
		return medicationOrder;
	}
}
