package org.zeus.HealthEnhancements;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class PatientToProvider {

	String m_id = "";
	String m_szPatientID = "";
	String m_szProviderID = "";

	PatientToProvider()
	{
		Bootstrap.getInstance();
	}

	private PatientToProvider(BasicDBObject dbObject)
	{
		m_id = ((ObjectId) dbObject.get("_id")).toString();
		m_szPatientID = dbObject.getString("m_szPatientID");
		m_szProviderID = dbObject.getString("m_szProviderID");	
	}

	private HashMap<String, ArrayList<PatientToProvider>> GetAllPatientsToProviders()
	{
		HashMap<String, ArrayList<PatientToProvider>> mpP2P = new HashMap<String, ArrayList<PatientToProvider>>();
		DBCursor dbObjects =  Bootstrap.getEHRdbHandle().getCollection("PatientToProviderMapping").find();

		while (dbObjects.hasNext()) 
		{
			DBObject dbObject = dbObjects.next();
			String szPatientID = ((BasicBSONObject) dbObject).getString("m_szPatientID");

			if (mpP2P.get(szPatientID) == null) 
				mpP2P.put(szPatientID, new ArrayList<PatientToProvider>());

			PatientToProvider p2p = new PatientToProvider((BasicDBObject) dbObject);

			mpP2P.get(szPatientID).add(p2p);
		}

		return mpP2P;
	}

	private HashMap<String, ArrayList<PatientToProvider>> GetAllProvidersToPatients()
	{
		HashMap<String, ArrayList<PatientToProvider>> mpP2P = new HashMap<String, ArrayList<PatientToProvider>>();
		DBCursor dbObjects =  Bootstrap.getEHRdbHandle().getCollection("PatientToProviderMapping").find();

		while (dbObjects.hasNext()) 
		{
			DBObject dbObject = dbObjects.next();
			String szProviderID = ((BasicBSONObject) dbObject).getString("m_szProviderID");

			if (mpP2P.get(szProviderID) == null) 
				mpP2P.put(szProviderID, new ArrayList<PatientToProvider>());

			PatientToProvider p2p = new PatientToProvider((BasicDBObject) dbObject);

			mpP2P.get(szProviderID).add(p2p);
		}

		return mpP2P;
	}

	public boolean IsProviderPrivilegedToViewPatientData(String szProviderID, String szPatientID)
	{
		HashMap<String, ArrayList<PatientToProvider>> mpP2P = GetAllProvidersToPatients();

		if(mpP2P.get(szProviderID) == null)
			return false;

		ArrayList<PatientToProvider> p2p = mpP2P.get(szProviderID);

		for(PatientToProvider element : p2p)
		{
			if(element.m_szPatientID.equals(szPatientID))
				return true;
		}

		return false;
	}

	public boolean AddPatientToProviderMapping(String szPatientID, String szProviderID) 
	{
		m_szPatientID = szPatientID;
		m_szProviderID = szProviderID;

		UserInfo user = new UserInfo();
		ProviderInfo provider = new ProviderInfo();

		if(!user.DoesPatientExist(szPatientID))
			throw new RuntimeException("{\"error\":\"The patient id that you entered does not exist in the system\"}");

		if(!provider.DoesProviderExist(szProviderID))
			throw new RuntimeException("{\"error\":\"The provider id that you entered does not exist in the system\"}");


		if(IsProviderPrivilegedToViewPatientData(szProviderID, szPatientID))
			throw new RuntimeException("{\"error\":\"This mapping already exists in the system\"}");

		DBCollection collection = Bootstrap.getEHRdbHandle().getCollection("PatientToProviderMapping");

		collection.insert(new BasicDBObject("m_szPatientID", m_szPatientID).append("m_szProviderID", m_szProviderID));

		return true;
	}

	public ArrayList<String> FindAllProviders(String szPatientID) 
	{
		HashMap<String, ArrayList<PatientToProvider>> mpP2P = GetAllPatientsToProviders();

		if(mpP2P.get(szPatientID) == null)
			throw new RuntimeException("{\"error\":\"patient does not exist in the mapping\"}");

		ArrayList<PatientToProvider> p2p = mpP2P.get(szPatientID);

		ArrayList<String> ProviderIds = new ArrayList<String>();

		for(PatientToProvider element : p2p)
		{
			ProviderIds.add(element.m_szProviderID);
		}

		return ProviderIds;
	}

	public ArrayList<String> FindAllPatients(String szProviderID) 
	{
		HashMap<String, ArrayList<PatientToProvider>> mpP2P = GetAllProvidersToPatients();

		if(mpP2P.get(szProviderID) == null)
			throw new RuntimeException("{\"error\":\"provider does not exist in the mapping\"}");

		ArrayList<PatientToProvider> p2p = mpP2P.get(szProviderID);

		ArrayList<String> PatientIds = new ArrayList<String>();

		for(PatientToProvider element : p2p)
		{
			PatientIds.add(element.m_szPatientID);
		}

		return PatientIds;
	}
}
