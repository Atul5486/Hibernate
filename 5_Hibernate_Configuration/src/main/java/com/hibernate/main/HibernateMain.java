package com.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.User;
import com.hibernate.utils.HibernateUtils;

public class HibernateMain {
	public static void main(String args[]) {
		User user = new User();
//		System.out.println("LocalDateTime.now() : "+LocalDateTime.now());
		
		user.setName("Andrew Anderson");
		user.setEmail("andrew123@gmail.com");
		user.setPassword("andrew@123");
		user.setAge(10);
		Session session = HibernateUtils.getSession().openSession();
		Transaction tx = null;
		// insertion of data
		try {
			tx = session.beginTransaction();
			session.persist(user);
			session.flush(); // (optional)
			tx.commit();
			System.out.println("Task Completed..!!");
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback takes place..!!");
			}
		}
		finally {
			session.close();
		}
		
		session=HibernateUtils.getSession().openSession();
		//Read all user data
		/*
		try {
			List<User> userList=session.createQuery("from User",User.class).getResultList();
			for(User list:userList) {
				System.out.println("\nUid : "+list.getUid());
				System.out.println("Username : "+list.getName());
				System.out.println("Email : "+list.getEmail());
				System.out.println("Password : "+list.getPassword());
				System.out.println("Age: "+list.getAge());
			}
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
		*/
		
		//Specific User data 
		/*
		try {
			
			User userObj=session.get(User.class,user.getUid());
			System.out.println(userObj.getName());
			System.out.println("Email : "+userObj.getEmail());
			System.out.println("Password : "+userObj.getPassword());
			
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}
		*/
		
		// Update User data
		
		/*
		try {
			tx=session.beginTransaction();
			User userData=session.get(User.class, user.getUid());
			userData.setName("Hello ji");
			userData.setEmail("Hello@gmail.com");
			userData.setPassword("Hello@123");
			userData.setAge(25);
			session.merge(userData);
			System.out.println("Data Updated successfully");
			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
		*/
		//Delete user data
		/*
		try {
			tx=session.beginTransaction();
			User userData=session.get(User.class, user.getUid());
			session.remove(userData);
			tx.commit();
			System.out.println("User deleted successfully");
			
		}catch(Exception e) {
			System.out.println("User deleted successfully");
		}
		*/
		
	}
}
