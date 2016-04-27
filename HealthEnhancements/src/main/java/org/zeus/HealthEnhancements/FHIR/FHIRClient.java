package org.zeus.HealthEnhancements.FHIR;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Appointment;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationStatement;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.param.DateRangeParam;

public class FHIRClient {

    public IGenericClient getClient(String serverBase){
      
      FhirContext ctx = FhirContext.forDstu2();
      
      IGenericClient client = ctx.newRestfulGenericClient(serverBase);

      return client;
    }

    public Bundle searchPatientByFamilyName(String baseurl, String familyName) {
      
      IGenericClient client = this.getClient(baseurl);

      // Perform a search
      Bundle results = client
            .search()
            .forResource(Patient.class)
            .where(Patient.FAMILY.matches().value("familyName"))
            .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
            .execute();
      return results;
    }

    public void createPatient(String baseurl, String first, String last, String pid){

        IGenericClient client = this.getClient(baseurl);
      // START SNIPPET: create
         Patient patient = new Patient();
         // ..populate the patient object..
         patient.addIdentifier().setSystem("urn:system").setValue(pid);
         patient.addName().addFamily(last).addGiven(first);

         // Invoke the server create method (and send pretty-printed JSON
         // encoding to the server
         // instead of the default which is non-pretty printed XML)
         MethodOutcome outcome = client.create()
            .resource(patient)
            .prettyPrint()
            .encodedJson()
            .execute();
         
         // The MethodOutcome object will contain information about the
         // response from the server, including the ID of the created 
         // resource, the OperationOutcome response, etc. (assuming that
         // any of these things were provided by the server! They may not
         // always be)
         IdDt id = (IdDt) outcome.getId();
         System.out.println("Got ID: " + id.getValue());
         // END SNIPPET: create
    }
    
    public List getMedicationsByPatientId(String baseurl, String patientid, String startdate, String enddate){
    	IGenericClient client = this.getClient(baseurl);
    	Bundle ms = client.search()
				.forResource(MedicationStatement.class).where(MedicationStatement.PATIENT.hasId(patientid))
				.returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
				.encodedJson().lastUpdated(new DateRangeParam(startdate, enddate)) // Date is of format "2014-11-01"
				.execute();
    	List<String> medications = new ArrayList<String>();
		for (Entry entry : ms.getEntry()){
			for(IResource iResource : entry.getResource().getContained().getContainedResources()){
				if(iResource.getResourceName().toString().equals("Medication")){
					Medication med = (Medication) iResource;
					for(CodingDt code : med.getCode().getCoding()){
						System.out.println("\n Medication Name :"+code.getDisplay());
						medications.add(code.getDisplay());
					}
				}
			}
		}
		return medications;

    }
    
    public Bundle getObservationsByPatientId(String baseurl, String patientid, String startdate, String enddate){
    	IGenericClient client = this.getClient(baseurl);
    	Bundle bundle = client.search().
                forResource(Observation.class).
                where(Observation.PATIENT.hasId(patientid))
                .encodedJson().lastUpdated(new DateRangeParam(startdate, enddate)) // Date is of format "2014-11-01"
				.include(Observation.INCLUDE_PATIENT).returnBundle(Bundle.class).execute();

        // Then
        for (Bundle.Entry entry : bundle.getEntry()) {
            if (entry.getResource() instanceof Observation) {
                Observation observation = (Observation) entry.getResource();
                CodingDt firstCoding = observation.getCode().getCodingFirstRep();
                System.out.println("Observation: code = " + firstCoding.getSystem() + "|" + firstCoding.getCode() + "|" + firstCoding.getDisplay());
            } else if (entry.getResource() instanceof  Patient) {
                Patient patient = (Patient) entry.getResource();
                System.out.println("Patient: name = " + patient.getNameFirstRep().getFamilyFirstRep() + ", " + patient.getNameFirstRep().getGivenFirstRep());
            }
        }
        return bundle;
    }
    
    public Bundle getDiagnosticReportByPatientId(String baseurl, String patientid, String startdate, String enddate){
    	IGenericClient client = this.getClient(baseurl);
    	Bundle bundle = client.search().
                forResource(DiagnosticReport.class).
                where(DiagnosticReport.PATIENT.hasId(patientid))
                .encodedJson().lastUpdated(new DateRangeParam(startdate, enddate)) // Date is of format "2014-11-01"
				.include(Observation.INCLUDE_PATIENT).returnBundle(Bundle.class).execute();

        // Then
        for (Bundle.Entry entry : bundle.getEntry()) {
            if (entry.getResource() instanceof DiagnosticReport) {
            	DiagnosticReport dreport = (DiagnosticReport) entry.getResource();
                String conclusion = dreport.getConclusion();
                System.out.println("Conclusion is : = " + conclusion);
            } else if (entry.getResource() instanceof  Patient) {
                Patient patient = (Patient) entry.getResource();
                System.out.println("Patient: name = " + patient.getNameFirstRep().getFamilyFirstRep() + ", " + patient.getNameFirstRep().getGivenFirstRep());
            }
        }
        return bundle;
    }
    
    public Bundle getAppointments (String baseurl, String patientid, String practionerid, String startdate, String enddate){
    	IGenericClient client = this.getClient(baseurl);
    	Bundle bundle = client.search().
                forResource(Appointment.class).
                where(Appointment.PATIENT.hasId(patientid))
                .encodedJson().lastUpdated(new DateRangeParam(startdate, enddate)) // Date is of format "2014-11-01"
				.include(Observation.INCLUDE_PATIENT).returnBundle(Bundle.class).execute();
    	
    	if(practionerid != null)
    		bundle = client.search().
                forResource(Appointment.class).
                where(Appointment.PATIENT.hasId(patientid))
                .where(Appointment.PRACTITIONER.hasId(practionerid))
                .encodedJson().lastUpdated(new DateRangeParam(startdate, enddate)) // Date is of format "2014-11-01"
				.include(Observation.INCLUDE_PATIENT).returnBundle(Bundle.class).execute();
    	// Then
        for (Bundle.Entry entry : bundle.getEntry()) {
            if (entry.getResource() instanceof Appointment) {
            	Appointment appt = (Appointment) entry.getResource();
            	Date startd = appt.getStart();
                System.out.println("Appointment start time is : = " + startd);
                Date endd = appt.getEnd();
                System.out.println("Appointment end time is : = " + endd);
            } else if (entry.getResource() instanceof  Patient) {
                Patient patient = (Patient) entry.getResource();
                System.out.println("Patient: name = " + patient.getNameFirstRep().getFamilyFirstRep() + ", " + patient.getNameFirstRep().getGivenFirstRep());
            }
        }
        return bundle;

    }
    
    public Bundle searchByPlainURL(IGenericClient client, String url){
    	// Search URL can also be a relative URL in which case the client's base
    	// URL will be added to it for example searchUrl = "Patient?identifier=foo"
    	return client.search()
    	      .byUrl(url)
    	      .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
    	      .execute();
    }
}