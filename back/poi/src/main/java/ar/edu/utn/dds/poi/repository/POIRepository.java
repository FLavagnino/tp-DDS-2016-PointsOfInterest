package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;

import org.hibernate.Session;

import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.utils.HibernateUtil;

public class POIRepository 
{
	public Serializable saveBusStop(BusStop busStop)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable busID = session.save(busStop);
        session.getTransaction().commit();
        session.close();
        
        return busID;
	}
	
	public void updateBusStop(BusStop busStop)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();  
        session.beginTransaction();
        session.update(busStop);
        session.getTransaction().commit();
        session.close();
	}
	
	public BusStop getBusStop(Serializable busID)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();
        BusStop busStop = session.get(BusStop.class, busID);
        session.close();
        
        return busStop;
	}
}
