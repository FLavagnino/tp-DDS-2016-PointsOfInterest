package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import ar.edu.utn.dds.poi.domain.Log;
import ar.edu.utn.dds.poi.domain.LogResult;

public class LogRepository 
{
	public Serializable saveHistoricalSearch(Log log)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
        
        for (LogResult result : log.getResults())
        {
        	result.setLog(log);
        }
        
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
	
	public List<Log> getLogByUserName(String userName)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        Query query = session.getNamedQuery("logByUser");
        query.setParameter("userName", userName);
        
        List<Log> results = (List<Log>)query.getResultList();
        session.close();
        
        return results;
	}
}
