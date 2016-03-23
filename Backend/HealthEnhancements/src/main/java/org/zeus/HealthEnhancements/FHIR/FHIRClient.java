package org.zeus.HealthEnhancements.FHIR;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.api.IBaseResource;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.base.resource.BaseOperationOutcome;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Conformance;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome;
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome.Issue;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Parameters;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Provenance;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.IssueSeverityEnum;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.SummaryEnum;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import ca.uhn.fhir.rest.method.SearchStyleEnum;
import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.server.exceptions.PreconditionFailedException;

public class FHIRClient {

    public IGenericClient getClient(String serverBase){
      
      FhirContext ctx = FhirContext.forDstu2();
      
      IGenericClient client = ctx.newRestfulGenericClient(serverBase);

      return client;
    }

    public Bundle searchPatientByFamilyName(String familyName) {
      
      IGenericClient client = this.getClient("");

      // Perform a search
      Bundle results = client
            .search()
            .forResource(Patient.class)
            .where(Patient.FAMILY.matches().value("familyName"))
            .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
            .execute();
      return results;
    }

    public void createPatient(String first, String last, String pid){

        IGenericClient client = this.getClient("");
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
}