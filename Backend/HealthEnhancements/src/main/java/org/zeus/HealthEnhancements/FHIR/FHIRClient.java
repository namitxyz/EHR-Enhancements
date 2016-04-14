package org.zeus.HealthEnhancements.FHIR;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationOrder;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.param.DateRangeParam;

public class FHIRClient {
	public static String baseurl = "http://polaris.i3l.gatech.edu:8080/gt-fhir-webapp/base";

    private static IGenericClient getClient(String serverBase){
      
      FhirContext ctx = FhirContext.forDstu2();
      
      IGenericClient client = ctx.newRestfulGenericClient(serverBase);

      return client;
    }

    public static Bundle searchPatientByFamilyName(String base, String familyName) {
    	
    	if (base != null)
    		baseurl = base;
      
      IGenericClient client = FHIRClient.getClient(baseurl);

      // Perform a search
      Bundle results = client
            .search()
            .forResource(Patient.class)
            .where(Patient.FAMILY.matches().value(familyName))
            .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
            .execute();
      for (Bundle.Entry entry : results.getEntry()) {
          if (entry.getResource() instanceof Patient) {
          	System.out.println("----------------------");
          	String patName = ((Patient) entry.getResource()).getName().get(0).getNameAsSingleString();
          	String patId = ((Patient) entry.getResource()).getId().toUnqualifiedVersionless().getIdPart();
          	System.out.println("Patient name is : = " + patName + " and id value is : = " + patId);
            System.out.println("----------------------");
          } 
      }
      return results;
    }
    
    public static Bundle getPatientById(String base, String id) {
    	if (base != null)
    		baseurl = base;
    	return (Bundle)searchByPlainURL(baseurl, "Patient?_id=" + id);
    }
    
    

    public static void createPatient(String base, String first, String last, String pid){

    	if (base != null)
    		baseurl = base;
    	IGenericClient client = getClient(baseurl);
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
    
    public static List getMedicationsByPatientId(String base, String patientid){
    	if (base != null)
    		baseurl = base;
    	IGenericClient client = getClient(baseurl);
    	Bundle ms = client.search()
				.forResource(MedicationOrder.class).where(MedicationOrder.PATIENT.hasId(patientid))
				.returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
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
    
    public static Bundle getObservationsByPatientId(String base, String patientid, String startdate, String enddate){
    	if (base != null)
    		baseurl = base;
    	IGenericClient client = getClient(baseurl);
    	Bundle bundle = client.search().
                forResource(Observation.class).
                where(Observation.PATIENT.hasId(patientid))
                //.encodedJson().lastUpdated(new DateRangeParam(startdate, enddate)) // Date is of format "2014-11-01"
				//.include(Observation.INCLUDE_PATIENT).returnBundle(Bundle.class).execute();
.returnBundle(Bundle.class).execute();
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
    
    public static Bundle getDiagnosticReportByPatientId(String base, String patientid, String startdate, String enddate){
    	if (base != null)
    		baseurl = base;
    	IGenericClient client = getClient(baseurl);
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
    
    public static Bundle getAppointments (String base, String patientid){
    	if (base != null)
    		baseurl = base;
    	
    	IGenericClient client = getClient(baseurl);
    	Bundle bundle = client.search().
                forResource(Encounter.class).
                where(Encounter.PATIENT.hasId(patientid))
                .returnBundle(Bundle.class).execute();
    	
    	for (Bundle.Entry entry : bundle.getEntry()) {
            if (entry.getResource() instanceof Encounter) {
            	System.out.println("----------------------");
            	Encounter enct = (Encounter) entry.getResource();
            	Date startd = enct.getPeriod().getStart();
                System.out.println("Appointment start time is : = " + startd);
                Date endd = enct.getPeriod().getEnd();
                System.out.println("Appointment end time is : = " + endd);
                String provider = enct.getServiceProvider().getReference().getValueAsString();
                System.out.println("Provider name is " + provider);
                System.out.println("----------------------");
            } 
        }
        return bundle;

    }
    
    public static Bundle searchByPlainURL(String base, String url){
    	
    	if (base != null)
    		baseurl = base;
    	
    	IGenericClient client = getClient(baseurl);

      // Search URL can also be a relative URL in which case the client's base
    	// URL will be added to it for example searchUrl = "Patient?identifier=foo"
    	return client.search()
    	      .byUrl(url)
    	      .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
    	      .execute();
    }
}