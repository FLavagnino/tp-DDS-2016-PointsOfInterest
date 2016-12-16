package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;
import org.hibernate.Session;
import ar.edu.utn.dds.poi.domain.JobResult;

public class JobRepository 
{
	public Serializable saveResult(JobResult result)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
          
        Serializable resultID = session.save(result);
        session.getTransaction().commit();
        
        session.close();
        
        return resultID;
	}	
}
