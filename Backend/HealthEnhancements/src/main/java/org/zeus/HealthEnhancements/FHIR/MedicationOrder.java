package org.zeus.HealthEnhancements.FHIR;

public class MedicationOrder {
	private String m_szPaientID = "";
	private String m_szDispenseRequestQuantity = "";
	private String m_szDosageInstrucionValue = "";
	private String m_szDosageInstructionUnit = "";
	private String m_szMedicationCode = "";
	private String m_szMedicationName = "";
	private String m_szDateWritten = "";
	private String m_szMedicationOrderID = "";
	private String m_szPhysicianID = "";
	private String m_szEncounterID = "";
	
	public String getPaientID() {
		return m_szPaientID;
	}
	public void setPaientID(String paientID) {
		m_szPaientID = paientID;
	}
	public String getDispenseRequest_quantity() {
		return m_szDispenseRequestQuantity;
	}
	public void setDispenseRequest_quantity(String dispenseRequest_quantity) {
		m_szDispenseRequestQuantity = dispenseRequest_quantity;
	}
	public String getDosageInstrucion_value() {
		return m_szDosageInstrucionValue;
	}
	public void setDosageInstrucion_value(String dosageInstrucion_value) {
		m_szDosageInstrucionValue = dosageInstrucion_value;
	}
	public String getDosageInstruction_unit() {
		return m_szDosageInstructionUnit;
	}
	public void setDosageInstruction_unit(String dosageInstruction_unit) {
		m_szDosageInstructionUnit = dosageInstruction_unit;
	}
	public String getMedicationCode() {
		return m_szMedicationCode;
	}
	public void setMedicationCode(String medicationCode) {
		m_szMedicationCode = medicationCode;
	}
	public String getMedicationName() {
		return m_szMedicationName;
	}
	public void setMedicationName(String medicationName) {
		m_szMedicationName = medicationName;
	}
	public String getDateWritten() {
		return m_szDateWritten;
	}
	public void setDateWritten(String dateWritten) {
		this.m_szDateWritten = dateWritten;
	}
	public String getMedicationOrderID() {
		return m_szMedicationOrderID;
	}
	public void setMedicationOrderID(String medicationOrderID) {
		m_szMedicationOrderID = medicationOrderID;
	}
	public String getPhysicianID() {
		return m_szPhysicianID;
	}
	public void setPhysicianID(String physicianID) {
		m_szPhysicianID = physicianID;
	}
	public String getEncounterID() {
		return m_szEncounterID;
	}
	public void setEncounterID(String encounterID) {
		m_szEncounterID = encounterID;
	}

}
