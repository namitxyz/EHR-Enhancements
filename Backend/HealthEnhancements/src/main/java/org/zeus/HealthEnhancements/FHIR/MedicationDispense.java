package org.zeus.HealthEnhancements.FHIR;

public class MedicationDispense {
	private String PatientID = "";
	private String DaysOfSupply = "";
	private String MediciationCode = "";
	private String DespenseID = "";
	private String QuantityValue = "";
	private String QuantityUnit = "";
	private String PreparedTime = "";
	
	public String getPatientID() {
		return PatientID;
	}
	public void setPatientID(String patientID) {
		PatientID = patientID;
	}
	public String getDaysOfSupply() {
		return DaysOfSupply;
	}
	public void setDaysOfSupply(String daysOfSupply) {
		DaysOfSupply = daysOfSupply;
	}
	public String getMediciationCode() {
		return MediciationCode;
	}
	public void setMediciationCode(String mediciationCode) {
		MediciationCode = mediciationCode;
	}
	public String getDespenseID() {
		return DespenseID;
	}
	public void setDespenseID(String despenseID) {
		DespenseID = despenseID;
	}
	public String getQuantityValue() {
		return QuantityValue;
	}
	public void setQuantityValue(String quantityValue) {
		QuantityValue = quantityValue;
	}
	public String getQuantityUnit() {
		return QuantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		QuantityUnit = quantityUnit;
	}
	public String getPreparedTime() {
		return PreparedTime;
	}
	public void setPreparedTime(String preparedTime) {
		PreparedTime = preparedTime;
	}
}
