package com.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Cricketer;

import jakarta.persistence.TypedQuery;

public class Main{
	public static void main(String args[]) {
		Session s=getSession().openSession();
		Transaction tx=null;
		try {
			tx=s.getTransaction();
			tx.begin();
			
			/*
			String query="select c from Cricketer c";
			TypedQuery<Cricketer> q=s.createQuery(query,Cricketer.class);			
			List<Cricketer> cricketer= q.getResultList();
			for(Cricketer c:cricketer) {
				System.out.println("Name :-" +c.getName());
			}
			*/
			/*
			String query="select c from Cricketer c where runs>:runs";
			TypedQuery<Cricketer> q=s.createQuery(query,Cricketer.class);			
			q.setParameter("runs", 2000);
			List<Cricketer> cricketer= q.getResultList();
			for(Cricketer c:cricketer) {
				System.out.println("Name :-" +c.getName());
			}
			*/
//			Pagination
			/*
			int pageNumber=1;
			int pageSize=2;
			String query="select c from Cricketer c";
			TypedQuery<Cricketer> q=s.createQuery(query,Cricketer.class);
			q.setFirstResult((pageNumber-1)*pageSize);
			q.setMaxResults(pageSize);
			
			
			List<Cricketer> cricketer = q.getResultList();
		 	for(Cricketer c : cricketer) {
				System.out.println(c.getName()+"\t"+c.getRuns()+"\t"+c.getAge()+"\t"+c.getJob()+"\t"+c.getWicket()+"\t");
		 	}
			*/
			
//			Aggregate Function
//			Count
			/*
			String query="select count(c) from Cricketer c";
			TypedQuery<Long>q=s.createQuery(query,Long.class);
			Long c=q.getSingleResult();
			System.out.println(c);			
			 */
			
//			Max
			/*
			String query="select max(c.runs) from Cricketer c";
			TypedQuery<Integer> q=s.createQuery(query,Integer.class);
			Integer max=q.getSingleResult();
			System.out.println(max);
			*/
			
//			Min
			/*
			String query="select min(c.runs) from Cricketer c";
			TypedQuery<Integer> q=s.createQuery(query,Integer.class);
			Integer min=q.getSingleResult();
			System.out.println(min);
			*/
			
//			sum
			/*
			String query="select sum(c.runs) from Cricketer c";
			TypedQuery<Object> q=s.createQuery(query,Object.class);
			Object sum=q.getSingleResult();
			System.out.println(sum);
			*/	
//			Avg
			/*
			String query="select avg(c.runs) from Cricketer c";
			TypedQuery<Double> q=s.createQuery(query,Double.class);
			Double avg=q.getSingleResult();
			System.out.println(avg);
		*/
			
			
		String query="select c from Cricketer c where runs>:runs";
		TypedQuery<Cricketer> q=s.createQuery(query,Cricketer.class);
		q.setParameter("runs", 5000);
		List<Cricketer> cric=q.getResultList();
		
		for(Cricketer c:cric) {
			System.out.println(c.getName()+" "+c.getRuns()+" "+c.getWicket()+" "+c.getJob());			
		}
			
			
			tx.commit();
			
			}catch(Exception e) {
				if(tx!=null) {
					tx.rollback();
					System.out.println("Rollback Takes palce");
				}
				System.out.println("Exception during commit :"+e);
			}
	}
	
	static SessionFactory getSession() {
		Configuration cfg=new Configuration();
		cfg.addAnnotatedClass(Cricketer.class);
		
		SessionFactory sf=null;
		try {
			sf=cfg.configure("hibernate.cfg.xml").buildSessionFactory();			
		}catch(Exception e) {
			System.out.println("Exception during creating session"+e);
		}
		
		return sf;
	}
}