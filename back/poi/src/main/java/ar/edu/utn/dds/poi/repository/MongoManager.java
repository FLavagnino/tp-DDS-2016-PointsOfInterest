package ar.edu.utn.dds.poi.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoManager 
{
    private static MongoManager instance = null;
    private MongoDatabase mongoDatabase;

    private MongoManager() 
    {
        MongoClient mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase("test");
    }

    public static MongoManager getInstance()
    {
        if(instance == null)
        {
            instance = new MongoManager();
        }

        return instance;
    }

    public MongoDatabase getMongoDatabase() 
    {
        return mongoDatabase;
    }
}
