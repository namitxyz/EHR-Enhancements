package org.zeus.HealthEnhancements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ProviderFeedback 
{

	String m_id = "";
	String m_szTimeStamp = "";
	String m_szPatientID = "";
	String m_szProviderID = "";
	String m_szFeedback = "";

	ProviderFeedback()
	{
		Bootstrap.getInstance();
	}

	private ProviderFeedback(BasicDBObject dbObject)
	{
		m_id = ((ObjectId) dbObject.get("_id")).toString();
		m_szTimeStamp = dbObject.getString("m_szTimeStamp");
		m_szPatientID = dbObject.getString("m_szPatientID");
		m_szProviderID = dbObject.getString("m_szProviderID");
		m_szFeedback = dbObject.getString("m_szFeedback");	
	}

	private HashMap<String, ArrayList<ProviderFeedback>> GetAllPatientsToProviders()
	{
		HashMap<String, ArrayList<ProviderFeedback>> mpP2P = new HashMap<String, ArrayList<ProviderFeedback>>();
		DBCursor dbObjects =  Bootstrap.getEHRdbHandle().getCollection("ProviderFeedback").find();

		while (dbObjects.hasNext()) 
		{
			DBObject dbObject = dbObjects.next();
			String szPatientID = ((BasicBSONObject) dbObject).getString("m_szPatientID");


			if (mpP2P.get(szPatientID) == null) 
				mpP2P.put(szPatientID, new ArrayList<ProviderFeedback>());

			ProviderFeedback p2p = new ProviderFeedback((BasicDBObject) dbObject);

			mpP2P.get(szPatientID).add(p2p);
		}

		return mpP2P;
	}

	private HashMap<String, ArrayList<ProviderFeedback>> GetAllProvidersToPatients()
	{
		HashMap<String, ArrayList<ProviderFeedback>> mpP2P = new HashMap<String, ArrayList<ProviderFeedback>>();
		DBCursor dbObjects =  Bootstrap.getEHRdbHandle().getCollection("ProviderFeedback").find();

		while (dbObjects.hasNext()) 
		{
			DBObject dbObject = dbObjects.next();
			String szProviderID = ((BasicBSONObject) dbObject).getString("m_szProviderID");
			

			if (mpP2P.get(szProviderID) == null) 
				mpP2P.put(szProviderID, new ArrayList<ProviderFeedback>());

			ProviderFeedback p2p = new ProviderFeedback((BasicDBObject) dbObject);

			mpP2P.get(szProviderID).add(p2p);
		}

		return mpP2P;
	}

	public ArrayList<ProviderFeedback> FindAllFeedbackForPatient(String szPatientID) 
	{
		HashMap<String, ArrayList<ProviderFeedback>> mpP2P = GetAllPatientsToProviders();

		if(mpP2P.get(szPatientID) == null)
			throw new RuntimeException("{\"error\":\"patient does not exist in the mapping\"}");

		ArrayList<ProviderFeedback> p2p = mpP2P.get(szPatientID);

		return p2p;
	}

	public ArrayList<ProviderFeedback> FindAllFeedbackForProvider(String szProviderID) 
	{
		HashMap<String, ArrayList<ProviderFeedback>> mpP2P = GetAllProvidersToPatients();

		if(mpP2P.get(szProviderID) == null)
			throw new RuntimeException("{\"error\":\"provider does not exist in the mapping\"}");

		ArrayList<ProviderFeedback> p2p = mpP2P.get(szProviderID);

		return p2p;
	}

	public boolean AddPatientToProviderFeedback(String szPatientID, String szProviderID, String szFeedback)
	{
		m_szTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		m_szPatientID = szPatientID;
		m_szProviderID = szProviderID;
		m_szFeedback = szFeedback;

		PatientToProvider p2p = new PatientToProvider();

		if(!p2p.IsProviderPrivilegedToViewPatientData(szProviderID, szPatientID))
			throw new RuntimeException("{\"error\":\"Patient to Provider mapping does not exist and you cannot provide feedback.\"}");

		DBCollection collection = Bootstrap.getEHRdbHandle().getCollection("ProviderFeedback");

		collection.insert(new BasicDBObject("m_szTimeStamp", m_szTimeStamp).
				append("m_szPatientID", m_szPatientID).
				append("m_szProviderID", m_szProviderID).
				append("m_szFeedback", m_szFeedback));

		return true;
	}

	public ArrayList<ProviderFeedback> GetAllFeedbackGivenByPatientForProvider(String szPatientID, String szProviderID)
	{		
		ArrayList<ProviderFeedback> vFeedback = FindAllFeedbackForProvider(szProviderID);

		ArrayList<ProviderFeedback> FilteredFeedback = new ArrayList<ProviderFeedback>();

		for(ProviderFeedback element : vFeedback)
		{
			if(element.m_szPatientID.equals(szPatientID))
				FilteredFeedback.add(element);
		}

		return FilteredFeedback;
	}
}
