package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;

import org.hibernate.Session;

import ar.edu.utn.dds.poi.domain.*;

public class POIRepository 
{
	public Serializable saveBusStop(BusStop busStop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable busID = session.save(busStop);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return busID;
	}
	
	public void updateBusStop(BusStop busStop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();  
        session.beginTransaction();
        session.update(busStop);
        session.flush();
        session.getTransaction().commit();
        session.close();
	}
	
	public BusStop getBusStop(Serializable busID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        BusStop busStop = session.get(BusStop.class, busID);
        session.close();
        
        return busStop;
	}
	
	public void deleteBusStop(Serializable busID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        BusStop busStop = session.get(BusStop.class, busID);
        session.delete(busStop);
        session.close();
	}
	
	public Serializable saveBank(Bank bank)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable bankID = session.save(bank);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return bankID;
	}
	
	public void updateBank(Bank bank)
	{
        Session session = HibernateManager.getSessionFactory().openSession();  
        session.beginTransaction();
        session.flush();
        
        session.update(bank);

        session.getTransaction().commit();
        session.close();
	}
	
	public Bank getBank(Serializable bankID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        Bank bank = session.get(Bank.class, bankID);
        session.close();
        
        return bank;
	}
	
	public void deleteBank(Bank bank)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
        
        Object persistentInstance = session.load(Bank.class, bank.getId());
        session.delete(persistentInstance);
        
        session.flush();
        session.getTransaction().commit();
        session.close();
	}
	
	public Serializable saveShop(Shop shop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable shopID = session.save(shop);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return shopID;
	}
	
	public void updateShop(Shop shop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();  
        session.beginTransaction();
        session.flush();
        
        session.update(shop);

        session.getTransaction().commit();
        session.close();
	}
	
	public Shop getShop(Serializable shopID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        Shop shop = session.get(Shop.class, shopID);
        session.close();
        
        return shop;
	}
	
	public void deleteShop(Shop shop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
        
        Object persistentInstance = session.load(Shop.class, shop.getId());
        session.delete(persistentInstance);
        
        session.flush();
        session.getTransaction().commit();
        session.close();
	}
	
	public Serializable saveCGP(CGP cgp)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        for (ZoneCoordenate coord : cgp.getZoneCoordenates())
        {
        	coord.setCgp(cgp);
        }
        
        for (OpeningHour hour : cgp.getOpeningHours())
        {
        	hour.setPoi(cgp);
        }
        
        Serializable cgpID = session.save(cgp);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return cgpID;
	}
	
	public void updateCGP(CGP cgp)
	{
        Session session = HibernateManager.getSessionFactory().openSession();  
        session.beginTransaction();
        session.flush();
        
        session.update(cgp);

        session.getTransaction().commit();
        session.close();
	}
	
	public CGP getCGP(Serializable cgpID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        CGP cgp = session.get(CGP.class, cgpID);
        session.close();
        
        return cgp;
	}
	
	public void deleteCGP(CGP cgp)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
        
        Object persistentInstance = session.load(CGP.class, cgp.getId());
        session.delete(persistentInstance);
        
        session.flush();
        session.getTransaction().commit();
        session.close();
	}
}
