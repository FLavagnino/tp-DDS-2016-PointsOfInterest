package ar.edu.utn.dds.poi.test;

import org.junit.Before;
import org.junit.Test;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.utils.*;
 
import org.hibernate.*;

public class Entrega5Test 
{	
	@Before
	public void setUp()
	{
	}
	
	@Test
	public void insertUserTest()
	{
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
 
        User user = new User();
        user.setUserName("luis");
        user.setPassword("1234");
//        user.setUserId(1);
        
        session.save(user);
      
        session.getTransaction().commit();
	}
}