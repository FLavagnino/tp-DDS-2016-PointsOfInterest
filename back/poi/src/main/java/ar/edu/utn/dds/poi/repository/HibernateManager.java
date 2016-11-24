package ar.edu.utn.dds.poi.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateManager 
{
	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() 
	{
		if(sessionFactory == null) 
		{
			sessionFactory = buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
	private static SessionFactory buildSessionFactory() 
	{	
		StandardServiceRegistry registry = null;
		
		try 
		{
			registry = new StandardServiceRegistryBuilder()
			        .configure() // configures settings from hibernate.cfg.xml
			        .build();
			
			return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			
			// Create the SessionFactory from hibernate.cfg.xml
			//Configuration configuration = new Configuration().configure();	
			//return configuration.buildSessionFactory();
		} 
		catch (Throwable ex) 
		{
		    // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
		    // so destroy it manually.
		    StandardServiceRegistryBuilder.destroy( registry );
		    
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void shutdown()
	{
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
