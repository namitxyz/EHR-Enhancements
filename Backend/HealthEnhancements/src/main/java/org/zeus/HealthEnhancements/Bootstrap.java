package org.zeus.HealthEnhancements;

import com.mongodb.*;

public class Bootstrap {
	private static Bootstrap instance = null;
	private static DB dbHandle = null;

	public static Bootstrap getInstance()
	{
		if (instance == null)
			instance  = new Bootstrap();

		return instance;
	}
	
	public static DB getEHRdbHandle()
	{
		return dbHandle;
	}

	private Bootstrap()
	{		
		try 
		{
			dbHandle = mongo();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private static DB mongo() throws Exception 
	{
		String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");

		if (host == null) 
		{
			MongoClient mongoClient = new MongoClient("localhost");
			return mongoClient.getDB("ehr");
		}

		int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
		String dbname = System.getenv("OPENSHIFT_APP_NAME");
		String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
		mongoClient.setWriteConcern(WriteConcern.SAFE);
		DB db = mongoClient.getDB(dbname);
		if (db.authenticate(username, password.toCharArray())) 
		{
			return db;
		} 
		else 
		{
			throw new RuntimeException("Not able to authenticate with MongoDB");
		}
	}
}
