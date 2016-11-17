package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;
import org.hibernate.Session;
import ar.edu.utn.dds.poi.domain.Log;

public class LogRepository 
{
	public Serializable saveHistoricalSearch(Log historicalSearch)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable historicalSearchID = session.save(historicalSearch);
        session.getTransaction().commit();
        session.close();
        
        return historicalSearchID;
	}
	
	public void update(Log historicalSearch)
	{
        Session session = HibernateManager.getSessionFactory().openSession();  
        session.beginTransaction();
        session.update(historicalSearch);
        session.getTransaction().commit();
        session.close();
	}
	
	public Log getHistoricalSearch(Serializable historicalSearchID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        Log historicalSearch = session.get(Log.class, historicalSearchID);
        session.close();
        
        return historicalSearch;
	}
}
