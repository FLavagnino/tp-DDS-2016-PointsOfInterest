package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;
import org.hibernate.Session;
import ar.edu.utn.dds.poi.domain.Log;

public class LogRepository 
{
	public Serializable saveHistoricalSearch(Log log)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable logID = session.save(log);
        session.getTransaction().commit();
        session.close();
        
        return logID;
	}
	
	public void update(Log log)
	{
        Session session = HibernateManager.getSessionFactory().openSession();  
        session.beginTransaction();
        session.update(log);
        session.getTransaction().commit();
        session.close();
	}
	
	public Log getLog(Serializable logID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        Log log = session.get(Log.class, logID);
        session.close();
        
        return log;
	}
}
