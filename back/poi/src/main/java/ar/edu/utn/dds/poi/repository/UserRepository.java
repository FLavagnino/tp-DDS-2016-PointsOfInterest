package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.edu.utn.dds.poi.domain.Action;
import ar.edu.utn.dds.poi.domain.Log;

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
	
	@SuppressWarnings("unchecked")
	public List<User> getAll()
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        Query query = session.getNamedQuery("all");
        
        List<User> results = (List<User>)query.getResultList();
        session.close();
        
        return results;
	}

	@SuppressWarnings("rawtypes")
	public User getUser(String userName) {
        Session session = HibernateManager.getSessionFactory().openSession();

        String hql = "from User where userName = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);

        User result = (User) query.getSingleResult();
        session.close();

        return result;
    }

    public User getUserWithToken(String userName, String token) {
        Session session = HibernateManager.getSessionFactory().openSession();

        String hql = "from User where userName = :userName and token = :token";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("token", token);

        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {}

        session.close();

        return user;
    }

    public User getUserWithPassword(String userName, String pass) {
        Session session = HibernateManager.getSessionFactory().openSession();

        String hql = "from User where userName = :userName and password = :pass";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("pass", pass);

        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {}

        session.close();

        return user;
    }
}
