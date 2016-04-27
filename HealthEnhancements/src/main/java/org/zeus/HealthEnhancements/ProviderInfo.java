package org.zeus.HealthEnhancements;

import java.util.HashMap;

import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ProviderInfo {

	String m_id = "";
	String m_szUserName = "";
	String m_szHashUserPassword = "";
	String m_szProviderType = "";
	String m_szFirstName = "";
	String m_szLastName = "";
	String m_szMiddleName = "";
	String m_szSocialSecurity = "";
	String m_szLicenseNumber = "";
	String m_szPersonnelType = "";
	String m_szSpecialization = "";
	String m_szAffiliation = "";
	String m_szAddress = "";
	String m_szPhoneNumber = "";
	String m_szEmail = "";

	public ProviderInfo()
	{
		Bootstrap.getInstance();
	}

	private ProviderInfo(BasicDBObject dbObject)
	{
		m_id = ((ObjectId) dbObject.get("_id")).toString();
		m_szUserName = dbObject.getString("m_szUserName");
		m_szHashUserPassword = dbObject.getString("m_szHashUserPassword");	
		m_szProviderType = dbObject.getString("m_szProviderType");	
		m_szFirstName = dbObject.getString("m_szFirstName");	
		m_szLastName = dbObject.getString("m_szLastName");	
		m_szMiddleName = dbObject.getString("m_szMiddleName");	
		m_szSocialSecurity  = dbObject.getString("m_szSocialSecurity");	
		m_szLicenseNumber  = dbObject.getString("m_szLicenseNumber");	
		m_szPersonnelType  = dbObject.getString("m_szPersonnelType");	
		m_szSpecialization  = dbObject.getString("m_szSpecialization");	
		m_szAffiliation  = dbObject.getString("m_szAffiliation");	
		m_szAddress = dbObject.getString("m_szAddress");	
		m_szPhoneNumber  = dbObject.getString("m_szPhoneNumber");	
		m_szEmail  = dbObject.getString("m_szEmail");	
	}

	private HashMap<String, ProviderInfo> GetAllProviders()
	{
		HashMap<String, ProviderInfo> mpProviders = new HashMap<String, ProviderInfo>();

		DBCursor dbObjects =  Bootstrap.getEHRdbHandle().getCollection("ProviderInfo").find();

		while (dbObjects.hasNext()) 
		{
			DBObject dbObject = dbObjects.next();
			mpProviders.put(((BasicBSONObject) dbObject).getString("m_szUserName"), new ProviderInfo((BasicDBObject) dbObject));
		}

		return mpProviders;
	}
	
	public boolean DoesProviderExist(String szUserName)
	{
		HashMap<String, ProviderInfo> mpProviders = GetAllProviders();
		
		if(mpProviders.containsKey(szUserName))
			return true;
		
		return false;
	}

	public boolean IsValidUser(String szUserName, String szPassword) 
	{
		m_szUserName = szUserName;
		m_szHashUserPassword = szPassword;

		HashMap<String, ProviderInfo> mpProviders = GetAllProviders();

		if(mpProviders.containsKey(m_szUserName)) 
		{
			ProviderInfo provider = mpProviders.get(m_szUserName);

			if(provider.m_szHashUserPassword.equals(m_szHashUserPassword))
				return true;
		}

		return false;
	}

	public boolean CreateProvider(String szUserName, String szPassword) 
	{
		m_szUserName = szUserName;
		m_szHashUserPassword = szPassword;

		HashMap<String, ProviderInfo> mpProviders = GetAllProviders();

		if(mpProviders.containsKey(m_szUserName))
			return false; // provider already exists in the system

		DBCollection collection = Bootstrap.getEHRdbHandle().getCollection("ProviderInfo");

		collection.insert(new BasicDBObject("m_szUserName", m_szUserName).append
				("m_szHashUserPassword", m_szHashUserPassword).append
				("m_szProviderType", m_szProviderType).append
				("m_szFirstName", m_szFirstName).append
				("m_szLastName", m_szLastName).append
				("m_szMiddleName", m_szMiddleName).append
				("m_szSocialSecurity", m_szSocialSecurity).append
				("m_szLicenseNumber", m_szLicenseNumber).append
				("m_szPersonnelType", m_szPersonnelType).append
				("m_szSpecialization", m_szSpecialization).append
				("m_szAffiliation", m_szAffiliation).append
				("m_szAddress", m_szAddress).append
				("m_szPhoneNumber", m_szPhoneNumber).append
				("m_szEmail", m_szEmail));

		LoadProviderProfile(szUserName, szPassword);

		return true;
	}

	public boolean UpdateProviderProfile() throws RuntimeException
	{
		HashMap<String, ProviderInfo> mpProviders = GetAllProviders();

		if(!mpProviders.containsKey(m_szUserName))
			throw new RuntimeException("{\"error\":\"Username does not exist in the system\"}");

		if(mpProviders.containsKey(m_szUserName)) 
		{
			ProviderInfo provider = mpProviders.get(m_szUserName);

			if(!provider.m_szHashUserPassword.equals(m_szHashUserPassword))
				throw new RuntimeException("{\"error\":\"Invalid Password\"}");

			if(!provider.m_id.equals(m_id))
				throw new RuntimeException("{\"error\":\"Invalid identifier in field m_id\"}");
		}

		DBCollection collection = Bootstrap.getEHRdbHandle().getCollection("ProviderInfo");

		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szProviderType", m_szProviderType)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szFirstName", m_szFirstName)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szLastName", m_szLastName)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szMiddleName", m_szMiddleName)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szSocialSecurity", m_szSocialSecurity)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szLicenseNumber", m_szLicenseNumber)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szPersonnelType", m_szPersonnelType)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szSpecialization", m_szSpecialization)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szAffiliation", m_szAffiliation)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szAddress", m_szAddress)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szPhoneNumber", m_szPhoneNumber)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmail", m_szEmail)));

		return true; 
	}

	public void LoadProviderProfile(String szUserName, String szPassword) 
	{
		m_szUserName = szUserName;
		m_szHashUserPassword = szPassword;

		HashMap<String, ProviderInfo> mpProviders = GetAllProviders();

		if(!mpProviders.containsKey(m_szUserName))
			throw new RuntimeException("{\"error\":\"Username does not exist in the system\"}");

		if(mpProviders.containsKey(m_szUserName)) 
		{
			ProviderInfo provider = mpProviders.get(m_szUserName);

			if(!provider.m_szHashUserPassword.equals(m_szHashUserPassword))
				throw new RuntimeException("{\"error\":\"Invalid Password\"}");

			this.m_id = provider.m_id;
			this.m_szProviderType = provider.m_szProviderType == null ? "" : provider.m_szProviderType;
			this.m_szFirstName = provider.m_szFirstName == null ? "" : provider.m_szFirstName;
			this.m_szLastName = provider.m_szLastName == null ? "" : provider.m_szLastName;
			this.m_szMiddleName = provider.m_szMiddleName == null ? "" : provider.m_szMiddleName;
			this.m_szSocialSecurity = provider.m_szSocialSecurity == null ? "" : provider.m_szSocialSecurity;
			this.m_szLicenseNumber = provider.m_szLicenseNumber == null ? "" : provider.m_szLicenseNumber;
			this.m_szPersonnelType = provider.m_szPersonnelType == null ? "" : provider.m_szPersonnelType;
			this.m_szSpecialization = provider.m_szSpecialization == null ? "" : provider.m_szSpecialization;
			this.m_szAffiliation = provider.m_szAffiliation == null ? "" : provider.m_szAffiliation;
			this.m_szAddress = provider.m_szAddress == null ? "" : provider.m_szAddress;
			this.m_szPhoneNumber = provider.m_szPhoneNumber == null ? "" : provider.m_szPhoneNumber;
			this.m_szEmail = provider.m_szEmail == null ? "" : provider.m_szEmail;
		}
	}
}
