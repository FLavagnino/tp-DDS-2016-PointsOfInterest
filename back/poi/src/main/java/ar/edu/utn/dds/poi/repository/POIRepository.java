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
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return busID;
	}
	
	public void updateBusStop(BusStop busStop)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();  
        session.beginTransaction();
        session.update(busStop);
        session.flush();
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
	
	public void deleteBusStop(Serializable busID)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();
        BusStop busStop = session.get(BusStop.class, busID);
        session.delete(busStop);
        session.close();
	}
	
	public Serializable saveBank(Bank bank)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable bankID = session.save(bank);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return bankID;
	}
	
	public void updateBank(Bank bank)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();  
        session.beginTransaction();
        session.flush();
        
        session.update(bank);

        session.getTransaction().commit();
        session.close();
	}
	
	public Bank getBank(Serializable bankID)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Bank bank = session.get(Bank.class, bankID);
        session.close();
        
        return bank;
	}
	
	public void deleteBank(Bank bank)
	{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Object persistentInstance = session.load(Bank.class, bank.getId());
        session.delete(persistentInstance);
        
        session.flush();
        session.getTransaction().commit();
        session.close();
	}
}
