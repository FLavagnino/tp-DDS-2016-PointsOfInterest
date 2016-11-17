package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;

import org.hibernate.Session;

import ar.edu.utn.dds.poi.domain.User;

public class UserRepository 
{
	public Serializable save(User user)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        session.beginTransaction();
        Serializable userID = session.save(user);
        session.getTransaction().commit();
        session.close();
        
        return userID;
	}
	
	public void update(User user)
	{
        Session session = HibernateManager.getSessionFactory().openSession();  
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
	}
	
	public User get(Serializable userID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        User user = session.get(User.class, userID);
        session.close();
        
        return user;
	}
}