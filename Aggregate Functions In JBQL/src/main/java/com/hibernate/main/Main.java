package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Cricketer;

import jakarta.persistence.Query;


public class Main{
	public static void main(String args[]) {
		Session session=getSession().openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			
			// Max Function
			/*
			
			 String query="select max(runs) from Cricketer";
			Query e  = session.createQuery(query);
			System.out.println("Maximum runs: "+e.getSingleResult());
			 */
			
//			Min Function
			/*
			String query="select min(runs) from Cricketer";
			Query e  = session.createQuery(query);
			System.out.println("Minimum runs: "+e.getSingleResult());
			 */
			
//			Sum function
			/*
			String query="select sum(runs) from Cricketer";
			Query e  = session.createQuery(query);
			System.out.println("All Player runs: "+e.getSingleResult());
			 */
			
//			Avg Function 
			
			/*
			String query="select avg(runs) from Cricketer";
			Query e  = session.createQuery(query);
			System.out.println("Average runs: "+e.getSingleResult());
			*/
			
//			Count function
			String query="select count(id) from Cricketer";
			Query e  = session.createQuery(query);
			System.out.println("Total No. of Players : "+e.getSingleResult());
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
		
	}
	static SessionFactory getSession() {
		SessionFactory sessionFactory=null;
		try {
			
		Configuration cfg=new Configuration();
		cfg.addAnnotatedClass(Cricketer.class);
		sessionFactory=cfg.configure("hibernate.cfg.xml").buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Exception during creating session factory :" +e);
		}
		
		return sessionFactory;
	}
}

//Just For creating cricketer
/*
Cricketer cric=new Cricketer();
cric.setName("Sheryash iyer");
cric.setAge(28);
cric.setJob("Batter");
cric.setRuns(6000);
cric.setWicket(10);

try {
	tx=session.beginTransaction();
	session.persist(cric);
	tx.commit();
	System.out.println("Data Inserted Successfully");
}catch(Exception e) {
	System.out.println("Exception during main execution : "+e);
}
*/