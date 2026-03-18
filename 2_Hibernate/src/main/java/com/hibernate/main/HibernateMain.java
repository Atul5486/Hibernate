package com.hibernate.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.User;
import com.hibernate.utils.HibernateUtils;

public class HibernateMain{
		public static void main(String args[]) {
			createDatabase();
			
			User user=new User();
			user.setUsername("Andrew Anderson");
			user.setEmail("andrew@gmail.com");
			user.setPassword("Andrew@123");
			user.setAddress("Indore");
			
			Session session=HibernateUtils.getSessionFactory().openSession();
			
			Transaction tx=null;
			try {
				tx=session.beginTransaction();
				session.persist(user);
				tx.commit();
			}catch(Exception e) {
				if(tx!=null) {
					System.out.println("Rollback Occurs");
					tx.rollback();
				}
			}
		}
		static void createDatabase() {
			
			String DRIVER="com.mysql.cj.jdbc.Driver";
			String URL="jdbc:mysql://localhost:3306/";
			String USER="root";
			String PASS="Airtel@123";
			
			try {				
			Class.forName(DRIVER);
			Connection con=DriverManager.getConnection(URL,USER,PASS);
			Statement st=con.createStatement();
			String query="create database if not exists itep16";
			st.execute(query);
			}catch(Exception e) {
				System.out.println("Exception : "+e);
			}
			
		}
}
