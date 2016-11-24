package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import ar.edu.utn.dds.poi.domain.*;

import javax.persistence.Query;

public class POIRepository 
{
	private Serializable saveBusStop(BusStop busStop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable busID = session.save(busStop);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return busID;
	}

    private void updateBusStop(BusStop busStop)
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

    private void deleteBusStop(BusStop busStop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();

        Object persistentInstance = session.load(Shop.class, busStop.getId());
        session.delete(persistentInstance);

        session.flush();
        session.getTransaction().commit();
        session.close();
	}

    private Serializable saveBank(Bank bank)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        for (OpeningHour hour : bank.getOpeningHours())
        {
        	hour.setPoi(bank);
        }
        
        Serializable bankID = session.save(bank);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return bankID;
	}

    private void updateBank(Bank bank)
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

    private void deleteBank(Bank bank)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
        
        Object persistentInstance = session.load(Bank.class, bank.getId());
        session.delete(persistentInstance);
        
        session.flush();
        session.getTransaction().commit();
        session.close();
	}

    private Serializable saveShop(Shop shop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        
        for (OpeningHour hour : shop.getOpeningHours())
        {
        	hour.setPoi(shop);
        }
        
        Serializable shopID = session.save(shop);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return shopID;
	}

    private void updateShop(Shop shop)
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

    private void deleteShop(Shop shop)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
        
        Object persistentInstance = session.load(Shop.class, shop.getId());
        session.delete(persistentInstance);
        
        session.flush();
        session.getTransaction().commit();
        session.close();
	}

    private Serializable saveCGP(CGP cgp)
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

    private void updateCGP(CGP cgp)
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

    private void deleteCGP(CGP cgp)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
        
        Object persistentInstance = session.load(CGP.class, cgp.getId());
        session.delete(persistentInstance);
        
        session.flush();
        session.getTransaction().commit();
        session.close();
	}

	public Serializable save(POI poi) {
        switch (poi.getType()) {
            case "cgp":
                return saveCGP((CGP) poi);
            case "bank":
                return saveBank((Bank) poi);
            case "bus_stop":
                return saveBusStop((BusStop) poi);
            case "shop":
                return saveShop((Shop) poi);
            default:
                return null;
        }
    }

    public void update(POI poi) {
        switch (poi.getType()) {
            case "cgp":
                updateCGP((CGP) poi);
                return;
            case "bank":
                updateBank((Bank) poi);
                return;
            case "bus_stop":
                updateBusStop((BusStop) poi);
                return;
            case "shop":
                updateShop((Shop) poi);
                return;
            default:
                return;
        }
    }

    public void delete(POI poi) {
        switch (poi.getType()) {
            case "cgp":
                deleteCGP((CGP) poi);
                return;
            case "bank":
                deleteBank((Bank) poi);
                return;
            case "bus_stop":
                deleteBusStop((BusStop) poi);
                return;
            case "shop":
                deleteShop((Shop) poi);
                return;
            default:
                return;
        }
    }

    public List<POI> getAllPois() {
        Session session = HibernateManager.getSessionFactory().openSession();

        String hql = "from POI";
        Query query = session.createQuery(hql);

        List results = query.getResultList();
        session.close();

        return results;
    }

}
