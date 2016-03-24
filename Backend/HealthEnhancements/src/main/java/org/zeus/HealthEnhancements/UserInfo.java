package org.zeus.HealthEnhancements;

import java.util.HashMap;
import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserInfo {

	String m_id = "";
	String m_szUserName = "";
	String m_szHashUserPassword = "";
	String m_szFirstName = "";
	String m_szLastName = "";
	String m_szMiddleInitial = "";
	String m_szAddress = "";
	String m_szPhoneNumber = "";
	String m_szEmail = "";
	String m_szDateOfBirth = "";
	String m_szGender = "";
	String m_szRace = "";
	String m_szEthnicity = "";
	String m_szPrimaryLanguage = "";
	String m_szMartialStatus = "";
	String m_szSocialSecurity = "";
	String m_szEmployer = "";
	String m_szEmploymentStatus = "";
	String m_szEmergencyFirstName = "";
	String m_szEmergencyLastName = "";
	String m_szEmergencyPhoneNumber = "";
	String m_szEmergencyRelationship = "";
	String m_szEmergencyAddress = "";
	String m_szInsuraceCompanyName = "";
	String m_szInsurancePhoneNumber = "";
	String m_szInsuranceSubsriberID = "";
	String m_szInsuranceGroupID = "";

	public UserInfo()
	{
		Bootstrap.getInstance();
	}

	private UserInfo(BasicDBObject dbObject)
	{
		m_id = ((ObjectId) dbObject.get("_id")).toString();
		m_szUserName = dbObject.getString("m_szUserName");
		m_szHashUserPassword = dbObject.getString("m_szHashUserPassword");
		m_szFirstName = dbObject.getString("m_szFirstName");
		m_szLastName = dbObject.getString("m_szLastName");
		m_szMiddleInitial = dbObject.getString("m_szMiddleInitial");
		m_szAddress = dbObject.getString("m_szAddress");
		m_szPhoneNumber = dbObject.getString("m_szPhoneNumber");
		m_szEmail = dbObject.getString("m_szEmail");
		m_szDateOfBirth = dbObject.getString("m_szDateOfBirth");
		m_szGender = dbObject.getString("m_szGender");
		m_szRace = dbObject.getString("m_szRace");
		m_szEthnicity = dbObject.getString("m_szEthnicity");
		m_szPrimaryLanguage = dbObject.getString("m_szPrimaryLanguage");
		m_szMartialStatus = dbObject.getString("m_szMartialStatus");
		m_szSocialSecurity = dbObject.getString("m_szSocialSecurity");
		m_szEmployer = dbObject.getString("m_szEmployer");
		m_szEmploymentStatus = dbObject.getString("m_szEmploymentStatus");
		m_szEmergencyFirstName = dbObject.getString("m_szEmergencyFirstName");
		m_szEmergencyLastName = dbObject.getString("m_szEmergencyLastName");
		m_szEmergencyPhoneNumber = dbObject.getString("m_szEmergencyPhoneNumber");
		m_szEmergencyRelationship = dbObject.getString("m_szEmergencyRelationship");
		m_szEmergencyAddress = dbObject.getString("m_szEmergencyAddress");
		m_szInsuraceCompanyName = dbObject.getString("m_szInsuraceCompanyName");
		m_szInsurancePhoneNumber = dbObject.getString("m_szInsurancePhoneNumber");
		m_szInsuranceSubsriberID = dbObject.getString("m_szInsuranceSubsriberID");
		m_szInsuranceGroupID = dbObject.getString("m_szInsuranceGroupID");
	}

	private HashMap<String, UserInfo> GetAllPatients()
	{
		HashMap<String, UserInfo> mpPatients = new HashMap<String, UserInfo>();

		DBCursor dbObjects =  Bootstrap.getEHRdbHandle().getCollection("UserInfo").find();

		while (dbObjects.hasNext()) 
		{
			DBObject dbObject = dbObjects.next();
			mpPatients.put(((BasicBSONObject) dbObject).getString("m_szUserName"), new UserInfo((BasicDBObject) dbObject));
		}

		return mpPatients;
	}
	
	public boolean DoesPatientExist(String szUserName)
	{
		HashMap<String, UserInfo> mpPatients = GetAllPatients();
		
		if(mpPatients.containsKey(szUserName))
			return true;
		
		return false;
	}
	
	public boolean IsValidUser(String szUserName, String szPassword)
	{
		m_szUserName = szUserName;
		m_szHashUserPassword = szPassword;

		HashMap<String, UserInfo> mpPatients = GetAllPatients();

		if(mpPatients.containsKey(m_szUserName)) 
		{
			UserInfo user = mpPatients.get(m_szUserName);

			if(user.m_szHashUserPassword.equals(m_szHashUserPassword))
				return true;
		}

		return false;
	}

	public boolean CreateUser(String szUserName, String szPassword)
	{
		m_szUserName = szUserName;
		m_szHashUserPassword = szPassword;

		HashMap<String, UserInfo> mpPatients = GetAllPatients();

		if(mpPatients.containsKey(m_szUserName))
			return false; // user already exists in the system

		DBCollection collection = Bootstrap.getEHRdbHandle().getCollection("UserInfo");

		collection.insert(new BasicDBObject("m_szUserName", m_szUserName).append
				("m_szHashUserPassword", m_szHashUserPassword).append
				("m_szFirstName", m_szFirstName).append
				("m_szLastName", m_szLastName).append
				("m_szMiddleInitial", m_szMiddleInitial).append
				("m_szAddress", m_szAddress).append
				("m_szPhoneNumber", m_szPhoneNumber).append
				("m_szEmail", m_szEmail).append
				("m_szDateOfBirth", m_szDateOfBirth).append
				("m_szGender", m_szGender).append
				("m_szRace", m_szRace).append
				("m_szEthnicity", m_szEthnicity).append
				("m_szPrimaryLanguage", m_szPrimaryLanguage).append
				("m_szMartialStatus", m_szMartialStatus).append
				("m_szSocialSecurity", m_szSocialSecurity).append
				("m_szEmployer", m_szEmployer).append
				("m_szEmploymentStatus", m_szEmploymentStatus).append
				("m_szEmergencyFirstName", m_szEmergencyFirstName).append
				("m_szEmergencyLastName", m_szEmergencyLastName).append
				("m_szEmergencyPhoneNumber", m_szEmergencyPhoneNumber).append
				("m_szEmergencyRelationship", m_szEmergencyRelationship).append
				("m_szEmergencyAddress", m_szEmergencyAddress).append
				("m_szInsuraceCompanyName", m_szInsuraceCompanyName).append
				("m_szInsurancePhoneNumber", m_szInsurancePhoneNumber).append
				("m_szInsuranceSubsriberID", m_szInsuranceSubsriberID).append
				("m_szInsuranceGroupID", m_szInsuranceGroupID));

		LoadPatientProfile(szUserName, szPassword);

		return true;
	}

	public boolean UpdatePatientProfile() throws RuntimeException
	{
		HashMap<String, UserInfo> mpPatients = GetAllPatients();

		if(!mpPatients.containsKey(m_szUserName))
			throw new RuntimeException("{\"error\":\"Username does not exist in the system\"}");

		if(mpPatients.containsKey(m_szUserName)) 
		{
			UserInfo user = mpPatients.get(m_szUserName);

			if(!user.m_szHashUserPassword.equals(m_szHashUserPassword))
				throw new RuntimeException("{\"error\":\"Invalid Password\"}");

			if(!user.m_id.equals(m_id))
				throw new RuntimeException("{\"error\":\"Invalid identifier in field m_id\"}");
		}

		DBCollection collection = Bootstrap.getEHRdbHandle().getCollection("UserInfo");

		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szFirstName", m_szFirstName)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szLastName", m_szLastName)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szMiddleInitial", m_szMiddleInitial)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szAddress", m_szAddress)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szPhoneNumber", m_szPhoneNumber)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmail", m_szEmail)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szDateOfBirth", m_szDateOfBirth)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szGender", m_szGender)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szRace", m_szRace)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEthnicity", m_szEthnicity)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szPrimaryLanguage", m_szPrimaryLanguage)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szMartialStatus", m_szMartialStatus)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szSocialSecurity", m_szSocialSecurity)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmployer", m_szEmployer)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmploymentStatus", m_szEmploymentStatus)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmploymentStatus", m_szEmploymentStatus)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmergencyFirstName", m_szEmergencyFirstName)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmergencyLastName", m_szEmergencyLastName)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmergencyPhoneNumber", m_szEmergencyPhoneNumber)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmergencyRelationship", m_szEmergencyRelationship)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szEmergencyAddress", m_szEmergencyAddress)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szInsuraceCompanyName", m_szInsuraceCompanyName)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szInsurancePhoneNumber", m_szInsurancePhoneNumber)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szInsuranceSubsriberID", m_szInsuranceSubsriberID)));
		collection.update(new BasicDBObject("_id", new ObjectId(m_id)), 
				new BasicDBObject("$set", new BasicDBObject("m_szInsuranceGroupID", m_szInsuranceGroupID)));		

		return true; 
	}

	public void LoadPatientProfile(String szUserName, String szPassword) 
	{
		m_szUserName = szUserName;
		m_szHashUserPassword = szPassword;

		HashMap<String, UserInfo> mpPatients = GetAllPatients();

		if(!mpPatients.containsKey(m_szUserName))
			throw new RuntimeException("{\"error\":\"Username does not exist in the system\"}");

		if(mpPatients.containsKey(m_szUserName)) 
		{
			UserInfo user = mpPatients.get(m_szUserName);

			if(!user.m_szHashUserPassword.equals(m_szHashUserPassword))
				throw new RuntimeException("{\"error\":\"Invalid Password\"}");

			this.m_id = user.m_id;
			this.m_szFirstName = user.m_szFirstName == null ? "" : user.m_szFirstName;
			this.m_szLastName = user.m_szLastName == null ? "" : user.m_szLastName;
			this.m_szMiddleInitial = user.m_szMiddleInitial == null ? "" : user.m_szMiddleInitial;
			this.m_szAddress = user.m_szAddress == null ? "" : user.m_szAddress;
			this.m_szPhoneNumber = user.m_szPhoneNumber == null ? "" : user.m_szPhoneNumber;
			this.m_szEmail = user.m_szEmail == null ? "" : user.m_szEmail;
			this.m_szDateOfBirth = user.m_szDateOfBirth == null ? "" : user.m_szDateOfBirth;
			this.m_szGender = user.m_szGender == null ? "" : user.m_szGender;
			this.m_szRace = user.m_szRace == null ? "" : user.m_szRace;
			this.m_szEthnicity = user.m_szEthnicity == null ? "" : user.m_szEthnicity;
			this.m_szPrimaryLanguage = user.m_szPrimaryLanguage == null ? "" : user.m_szPrimaryLanguage;
			this.m_szMartialStatus = user.m_szMartialStatus == null ? "" : user.m_szMartialStatus;
			this.m_szSocialSecurity = user.m_szSocialSecurity == null ? "" : user.m_szSocialSecurity;
			this.m_szEmployer = user.m_szEmployer == null ? "" : user.m_szEmployer;
			this.m_szEmploymentStatus = user.m_szEmploymentStatus == null ? "" : user.m_szEmploymentStatus;
			this.m_szEmergencyFirstName = user.m_szEmergencyFirstName == null ? "" : user.m_szEmergencyFirstName;
			this.m_szEmergencyLastName = user.m_szEmergencyLastName == null ? "" : user.m_szEmergencyLastName;
			this.m_szEmergencyPhoneNumber = user.m_szEmergencyPhoneNumber == null ? "" : user.m_szEmergencyPhoneNumber;
			this.m_szEmergencyRelationship = user.m_szEmergencyRelationship == null ? "" : user.m_szEmergencyRelationship;
			this.m_szEmergencyAddress = user.m_szEmergencyAddress == null ? "" : user.m_szEmergencyAddress;
			this.m_szInsuraceCompanyName = user.m_szInsuraceCompanyName == null ? "" : user.m_szInsuraceCompanyName;
			this.m_szInsurancePhoneNumber = user.m_szInsurancePhoneNumber == null ? "" : user.m_szInsurancePhoneNumber;
			this.m_szInsuranceSubsriberID = user.m_szInsuranceSubsriberID == null ? "" : user.m_szInsuranceSubsriberID;
			this.m_szInsuranceGroupID = user.m_szInsuranceGroupID == null ? "" : user.m_szInsuranceGroupID;	
		}
	}
}
