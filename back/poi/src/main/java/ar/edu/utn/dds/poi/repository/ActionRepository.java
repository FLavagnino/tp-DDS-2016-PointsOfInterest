package ar.edu.utn.dds.poi.repository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;
import ar.edu.utn.dds.poi.domain.User;
import org.hibernate.Session;
import ar.edu.utn.dds.poi.domain.Action;

public class ActionRepository 
{
	@SuppressWarnings("unchecked")
	public List<Action> getActionsByUserID(Serializable userID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        
        Query query = session.getNamedQuery("actionsByUserID");
        query.setParameter("user_id", userID);
        
        List<Action> results = (List<Action>)query.getResultList();
        session.close();
        
        return results;
	}

    public List<Action> getActionsByUser(User user)
    {
        Session session = HibernateManager.getSessionFactory().openSession();

        String hql = "from User where userName = :userName";
        Query query = session.createQuery(hql);
        query.setParameter("userName", user.getUserName());

        User result = (User)query.getSingleResult();
        session.close();

        return result.getActions();
    }
	
    public Serializable save(Action action)
	{
        Session session = HibernateManager.getSessionFactory().openSession();

        session.beginTransaction();


        Serializable actionID = session.save(action);
        session.flush();
        session.getTransaction().commit();
        session.close();
        
        return 2;
	}

    public void update(Action action)
	{
        Session session = HibernateManager.getSessionFactory().openSession();  
        session.beginTransaction();
        session.flush();
        session.update(action);

        session.getTransaction().commit();
        session.close();
	}

    public Action get(Serializable actionID)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        Action action = session.get(Action.class, actionID);
        session.close();
        
        return action;
	}

    public void delete(Action action)
	{
        Session session = HibernateManager.getSessionFactory().openSession();
        session.beginTransaction();
        
        Object persistentInstance = session.load(Action.class, action.getId());
        session.delete(persistentInstance);
        
        session.flush();
        session.getTransaction().commit();
        session.close();
	}

	public List<Serializable> saveAll(List<Action> actions) {
        return actions.stream().map(this::save).collect(Collectors.toList());
    }

    public void deleteAll(List<Action> actions) {
        actions.stream().forEach(this::delete);
    }
}
