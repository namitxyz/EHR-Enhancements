package org.zeus.HealthEnhancements;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Appointment 
{
	String m_id = "";
	String m_szTimeStamp = "";
	String m_szPatientID = "";
	String m_szProviderID = "";
	String m_szNotes = "";

	Appointment()
	{
		Bootstrap.getInstance();
	}

	private Appointment(BasicDBObject dbObject)
	{
		m_id = ((ObjectId) dbObject.get("_id")).toString();
		m_szTimeStamp = dbObject.getString("m_szTimeStamp");
		m_szPatientID = dbObject.getString("m_szPatientID");
		m_szProviderID = dbObject.getString("m_szProviderID");
		m_szNotes = dbObject.getString("m_szNotes");	
	}

	private HashMap<String, ArrayList<Appointment>> GetAllPatientsToProviders()
	{
		HashMap<String, ArrayList<Appointment>> mpP2P = new HashMap<String, ArrayList<Appointment>>();
		DBCursor dbObjects =  Bootstrap.getEHRdbHandle().getCollection("Appointment").find();

		while (dbObjects.hasNext()) 
		{
			DBObject dbObject = dbObjects.next();
			String szPatientID = ((BasicBSONObject) dbObject).getString("m_szPatientID");


			if (mpP2P.get(szPatientID) == null) 
				mpP2P.put(szPatientID, new ArrayList<Appointment>());

			Appointment p2p = new Appointment((BasicDBObject) dbObject);

			mpP2P.get(szPatientID).add(p2p);
		}

		return mpP2P;
	}

	private HashMap<String, ArrayList<Appointment>> GetAllProvidersToPatients()
	{
		HashMap<String, ArrayList<Appointment>> mpP2P = new HashMap<String, ArrayList<Appointment>>();
		DBCursor dbObjects =  Bootstrap.getEHRdbHandle().getCollection("Appointment").find();

		while (dbObjects.hasNext()) 
		{
			DBObject dbObject = dbObjects.next();
			String szProviderID = ((BasicBSONObject) dbObject).getString("m_szProviderID");


			if (mpP2P.get(szProviderID) == null) 
				mpP2P.put(szProviderID, new ArrayList<Appointment>());

			Appointment p2p = new Appointment((BasicDBObject) dbObject);

			mpP2P.get(szProviderID).add(p2p);
		}

		return mpP2P;
	}

	public ArrayList<Appointment> FindAllAppointmentsForPatient(String szPatientID) 
	{
		HashMap<String, ArrayList<Appointment>> mpP2P = GetAllPatientsToProviders();

		if(mpP2P.get(szPatientID) == null)
			throw new RuntimeException("{\"error\":\"patient does not exist in the mapping\"}");

		ArrayList<Appointment> p2p = mpP2P.get(szPatientID);

		return p2p;
	}

	public ArrayList<Appointment> FindAllAppointmentForProvider(String szProviderID) 
	{
		HashMap<String, ArrayList<Appointment>> mpP2P = GetAllProvidersToPatients();

		if(mpP2P.get(szProviderID) == null)
			throw new RuntimeException("{\"error\":\"provider does not exist in the mapping\"}");

		ArrayList<Appointment> p2p = mpP2P.get(szProviderID);

		return p2p;
	}

	public boolean AddPatientToProviderAppointment(String szPatientID, String szProviderID, String szNotes, String szDateTime)
	{
		m_szTimeStamp = szDateTime;
		m_szPatientID = szPatientID;
		m_szProviderID = szProviderID;
		m_szNotes = szNotes;

		PatientToProvider p2p = new PatientToProvider();

		if(!p2p.IsProviderPrivilegedToViewPatientData(szProviderID, szPatientID))
			throw new RuntimeException("{\"error\":\"Patient to Provider mapping does not exist and you cannot make an appointment.\"}");

		DBCollection collection = Bootstrap.getEHRdbHandle().getCollection("Appointment");

		collection.insert(new BasicDBObject("m_szTimeStamp", m_szTimeStamp).
				append("m_szPatientID", m_szPatientID).
				append("m_szProviderID", m_szProviderID).
				append("m_szNotes", m_szNotes));

		return true;
	}

	public ArrayList<Appointment> GetAllAppointmentsGivenByPatientForProvider(String szPatientID, String szProviderID)
	{		
		ArrayList<Appointment> vAppointment = FindAllAppointmentForProvider(szProviderID);

		ArrayList<Appointment> FilteredAppointment = new ArrayList<Appointment>();

		for(Appointment element : vAppointment)
		{
			if(element.m_szPatientID.equals(szPatientID))
				FilteredAppointment.add(element);
		}

		return FilteredAppointment;
	}
}
