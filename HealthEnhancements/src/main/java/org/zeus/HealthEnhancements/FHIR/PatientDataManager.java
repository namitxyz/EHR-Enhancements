package org.zeus.HealthEnhancements.FHIR;

import java.util.ArrayList;
import java.util.List;

import org.zeus.HealthEnhancements.Bootstrap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class PatientDataManager {
	public static List GetAllPatients(){
		List<Patient> patients = new ArrayList<Patient>();
		DB dbHandle =  Bootstrap.getInstance().getEHRdbHandle();
		BasicDBObject allQuery = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject();
		fields.put("_id", 0);
		fields.put("PatientID", 1);
		fields.put("BirthDate", 1);
		fields.put("Last_Name", 1);
		fields.put("First_Name", 1);
		fields.put("Gender", 1);
		fields.put("ZipCode", 1);
		fields.put("City", 1);
		fields.put("State", 1);
		fields.put("use", 1);
		fields.put("Address", 1);
		
		DBCursor cursor = dbHandle.getCollection("PATIENTS").find(allQuery, fields);
		while (cursor.hasNext()) {
			Patient patient = new Patient();
			DBObject dbobject = cursor.next();
			patient.setPatientID(String.valueOf(dbobject.get("PatientID")));
			patient.setBirthDate(String.valueOf(dbobject.get("BirthDate")));
			patient.setPatientID(String.valueOf(dbobject.get("PatientID")));
			patient.setLast_Name(String.valueOf(dbobject.get("Last_Name")));
			patient.setFirstName(String.valueOf(dbobject.get("First_Name")));
			patient.setMiddleInitial(String.valueOf(dbobject.get("Middle_Initial")));
			patient.setGender(String.valueOf(dbobject.get("Gender")));
			patient.setZipCode(String.valueOf(dbobject.get("ZipCode")));
			patient.setCity(String.valueOf(dbobject.get("City")));
			patient.setState(String.valueOf(dbobject.get("State")));
			patient.setAddress(String.valueOf(dbobject.get("Address")));
			patient.setUse(String.valueOf(dbobject.get("use")));
			patients.add(patient);
		}
		return patients;
	}
	
	public static Patient GetData(String patientid){
		Patient patient = new Patient();
		DB dbHandle =  Bootstrap.getInstance().getEHRdbHandle();

		BasicDBObject allQuery = new BasicDBObject("PatientID",Integer.parseInt(patientid));
		BasicDBObject fields = new BasicDBObject();
		fields.put("_id", 0);
		fields.put("BirthDate", 1);
		fields.put("Last_Name", 1);
		fields.put("Fisrt_Name", 1);
		fields.put("Gender", 1);
		fields.put("ZipCode", 1);
		fields.put("City", 1);
		fields.put("State", 1);
		fields.put("Use", 1);
		fields.put("Address", 1);
		
		DBCursor cursor = dbHandle.getCollection("PATIENTS").find(allQuery, fields);
		while (cursor.hasNext()) {
			DBObject dbobject = cursor.next();
			patient.setBirthDate(String.valueOf(dbobject.get("BirthDate")));
			patient.setPatientID(String.valueOf(dbobject.get("PatientID")));
			patient.setLast_Name(String.valueOf(dbobject.get("Last_Name")));
			patient.setFirstName(String.valueOf(dbobject.get("First_Name")));
			patient.setMiddleInitial(String.valueOf(dbobject.get("Middle_Initial")));
			patient.setGender(String.valueOf(dbobject.get("Gender")));
			patient.setAddress(String.valueOf(dbobject.get("Address")));
			patient.setZipCode(String.valueOf(dbobject.get("ZipCode")));
			patient.setCity(String.valueOf(dbobject.get("City")));
			patient.setState(String.valueOf(dbobject.get("State")));
			patient.setUse(String.valueOf(dbobject.get("use")));
		}
		
		BasicDBObject fields2 = new BasicDBObject();
		fields2.put("_id", 0);
		fields2.put("ConditionCode", 1);
		fields2.put("ConditionDisplay", 1);
		fields2.put("ConditionID", 1);
		fields2.put("EncounterID", 1);
		fields2.put("OnsetTime", 1);
		fields2.put("PatientID", 1);
		fields2.put("PhysicianID", 1);
		fields2.put("SeverityCode", 1);
		fields2.put("SeverityDisplay", 1);
		fields2.put("VerificationStatus", 1);
		
		DBCursor condcursor = dbHandle.getCollection("CONDITION").find(allQuery, fields2);
		while (condcursor.hasNext()) {
			Condition cond = new Condition();
			DBObject dbobject = condcursor.next();
			cond.setConditionCode(String.valueOf(dbobject.get("ConditionCode")));
			cond.setConditionDisplay(String.valueOf(dbobject.get("ConditionDisplay")));
			cond.setConditionID(String.valueOf(dbobject.get("ConditionID")));
			cond.setEncounterID(String.valueOf(dbobject.get("EncounterID")));
			cond.setNotes(String.valueOf(dbobject.get("notes")));
			cond.setOnsetTime(String.valueOf(dbobject.get("OnsetTime")));
			cond.setPatientID(String.valueOf(dbobject.get("PatientID")));
			cond.setPhysicianID(String.valueOf(dbobject.get("PhysicianID")));
			cond.setSeverityCode(String.valueOf(dbobject.get("SeverityCode")));
			cond.setSeverityDisplay(String.valueOf(dbobject.get("SeverityDisplay")));
			cond.setVerificationStatus(String.valueOf(dbobject.get("VerificationStatus")));
			patient.getConditions().add(cond);
		}
		
		BasicDBObject fields3 = new BasicDBObject();
		fields3.put("_id", 0);
		fields3.put("EncounterClass", 1);
		fields3.put("EncounterID", 1);
		fields3.put("EncounterTime", 1);
		fields3.put("EpisodeofCareID", 1);
		fields3.put("LocationID", 1);
		fields3.put("PatientID", 1);
		fields3.put("ProviderOrganizationID", 1);
		
		DBCursor enccursor = dbHandle.getCollection("ENCOUNTER").find(allQuery, fields3);
		while (enccursor.hasNext()) {
			Encounter encounter = new Encounter();
			DBObject dbobject = enccursor.next();
			encounter.setEncounterClass(String.valueOf(dbobject.get("EncounterClass")));
			encounter.setEncounterID(String.valueOf(dbobject.get("EncounterID")));
			encounter.setEncounterTime(String.valueOf(dbobject.get("EncounterTime")));
			encounter.setEpisodeofCareID(String.valueOf(dbobject.get("EpisodeofCareID")));
			encounter.setLocationID(String.valueOf(dbobject.get("LocationID")));
			encounter.setPatientID(String.valueOf(dbobject.get("PatientID")));
			encounter.setProviderOrganizationID(String.valueOf(dbobject.get("ProviderOrganizationID")));
			patient.getEncounters().add(encounter);
		}
		
		BasicDBObject fields4 = new BasicDBObject();
		fields4.put("_id", 0);
		fields4.put("dateWritten", 1);
		fields4.put("dispenseRequest_quantity", 1);
		fields4.put("dosageInstruction_value", 1);
		fields4.put("dosageInstruction_unit", 1);
		fields4.put("MedicationCode", 1);
		fields4.put("MedicationName", 1);
		fields4.put("MedicationOrderID", 1);
		fields4.put("PatientID", 1);
		fields4.put("PhysicianID", 1);
		
		DBCursor medordcursor = dbHandle.getCollection("MEDICATIONORDERS").find(allQuery, fields4);
		while (medordcursor.hasNext()) {
			MedicationOrder medorder = new MedicationOrder();
			DBObject dbobject = medordcursor.next();
			medorder.setDateWritten(String.valueOf(dbobject.get("dateWritten")));
			medorder.setDispenseRequest_quantity(String.valueOf(dbobject.get("dispenseRequest_quantity")));
			medorder.setDosageInstrucion_value(String.valueOf(dbobject.get("dosageInstruction_value")));
			medorder.setDosageInstruction_unit(String.valueOf(dbobject.get("dosageInstruction_unit")));
			medorder.setEncounterID(String.valueOf(dbobject.get("EncounterID")));
			medorder.setMedicationCode(String.valueOf(dbobject.get("MedicationCode")));
			medorder.setMedicationName(String.valueOf(dbobject.get("MedicationName")));
			medorder.setMedicationOrderID(String.valueOf(dbobject.get("MedicationOrderID")));
			medorder.setPaientID(String.valueOf(dbobject.get("PatientID")));
			medorder.setPhysicianID(String.valueOf(dbobject.get("PhysicianID")));
			patient.getMedicationOrder().add(medorder);
		}
		
		BasicDBObject fields5 = new BasicDBObject();
		fields.put("_id", 0);
		fields5.put("EncounterID", 1);
		fields5.put("ObservationCode", 1);
		fields5.put("ObservationDisplay", 1);
		fields5.put("ObservationEffectiveTime", 1);
		fields5.put("ObservationID", 1);
		fields5.put("ObservationStatues", 1);
		fields5.put("ObservationUnit", 1);
		fields5.put("ObservationValue", 1);
		fields5.put("PatientID", 1);
		DBCursor obscursor = dbHandle.getCollection("OBSERVATION").find(allQuery, fields5);
		while (obscursor.hasNext()) {
			Observation observation = new Observation();
			DBObject dbobject = obscursor.next();
			observation.setEncounterID(String.valueOf(dbobject.get("EncounterID")));
			observation.setObservationCode(String.valueOf(dbobject.get("ObservationCode")));
			observation.setObservationDisplay(String.valueOf(dbobject.get("ObservationDisplay")));
			observation.setObservationEffectiveTime(String.valueOf(dbobject.get("ObservationEffectiveTime")));
			observation.setObservationID(String.valueOf(dbobject.get("ObservationID")));
			observation.setObservationStatues(String.valueOf(dbobject.get("ObservationStatues")));
			observation.setObservationUnit(String.valueOf(dbobject.get("ObservationUnit")));
			observation.setObservationValue(String.valueOf(dbobject.get("ObservationValue")));
			observation.setPatientID(String.valueOf(dbobject.get("PatientID")));
			patient.getObservations().add(observation);
		}
		
		BasicDBObject fields6 = new BasicDBObject();
		fields.put("_id", 0);
		fields5.put("daysOfSupply", 1);
		fields5.put("DespenseID", 1);
		fields5.put("MediciationCode", 1);
		fields5.put("PatientID", 1);
		fields5.put("PreparedTime", 1);
		fields5.put("quantity_unit", 1);
		fields5.put("quantity_value", 1);
		
		DBCursor meddispcursor = dbHandle.getCollection("MEDICATIONDISPENSE").find(allQuery, fields6);
		while (meddispcursor.hasNext()) {
			MedicationDispense meddis = new MedicationDispense();
			DBObject dbobject = meddispcursor.next();
			meddis.setDaysOfSupply(String.valueOf(dbobject.get("daysOfSupply")));
			meddis.setDespenseID(String.valueOf(dbobject.get("DespenseID")));
			meddis.setMediciationCode(String.valueOf(dbobject.get("MediciationCode")));
			meddis.setPatientID(String.valueOf(dbobject.get("PatientID")));
			meddis.setPreparedTime(String.valueOf(dbobject.get("PreparedTime")));
			meddis.setQuantityUnit(String.valueOf(dbobject.get("quantity_unit")));
			meddis.setQuantityValue(String.valueOf(dbobject.get("quantity_value")));
			patient.getMedicationDispense().add(meddis);
		}
		return patient;
	}
	
	
}
