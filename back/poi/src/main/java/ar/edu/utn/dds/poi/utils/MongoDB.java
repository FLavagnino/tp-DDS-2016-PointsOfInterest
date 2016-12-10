package ar.edu.utn.dds.poi.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDB 
{
    private static MongoDB instance = null;
    private MongoDatabase mongoDatabase;

    private MongoDB() 
    {
        MongoClient mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase("test");
    }

    public static MongoDB getInstance()
    {
        if(instance == null)
        {
            instance = new MongoDB();
        }

        return instance;
    }

    public MongoDatabase getMongoDatabase() 
    {
        return mongoDatabase;
    }
}
