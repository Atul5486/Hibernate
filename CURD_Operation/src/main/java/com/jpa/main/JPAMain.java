package com.jpa.main;


import java.util.List;
import java.util.Scanner;

import com.jpa.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class JPAMain{
	public static void main(String args[]) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("unit");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=null;
		Scanner inp=new Scanner(System.in);
		User user=new User();
		select(user,inp,em,tx);
		em.close();
		emf.close();
	}
	static void select(User user,Scanner inp,EntityManager em,EntityTransaction tx) {
		boolean check=true;
		while(check) {
			System.out.println("\nEnter number to perform operation");
			System.out.println("1.Create a User");
			System.out.println("2.Update a User");
			System.out.println("3.Delete a User");
			System.out.println("4.Read all User");
			System.out.println("5.Search user by Emails");
			System.out.println("6.Exit");
			int choice=inp.nextInt();
			switch(choice) {
			case 1:createUser(user,inp,em,tx);break;
			case 2:updateUser(user,inp,em,tx);break;
			case 3:deleteUser(user,inp,em,tx);break;
			case 4:readUser(user,em,tx);break;
			case 5:searchUser(user,inp,em,tx);break;
			case 6:System.out.println("Exiting ...");check=false;break;
			default:System.out.println("Invalid choice");
			}
		}
		
	}
	static void createUser(User user,Scanner inp,EntityManager em,EntityTransaction tx) {
		String name,email,password;
		int age;
		inp.nextLine();
		System.out.println("\n------ Creating a user Here ------ \n");	
		System.out.print("Enter User name : ");
		name=inp.nextLine();
		System.out.print("Enter user email : ");
		email=inp.nextLine();
		System.out.print("Enter user password: ");
		password=inp.nextLine();
		System.out.print("Enter user age : ");
		age=inp.nextInt();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setAge(age);
		try {
			tx=em.getTransaction();
			tx.begin();
			em.persist(user);
			tx.commit();
		}catch(Exception e) {
			System.out.println("Exception during creating user : "+e);
		}
		System.out.println("User created Successfully");
	}
	static void updateUser(User user,Scanner inp,EntityManager em,EntityTransaction tx) {
		String name,email,password;
		int age;
		System.out.println("\n<----- Update user ------>\n");
		inp.nextLine();
		System.out.print("Enter User name : ");
		name=inp.nextLine();
		System.out.print("Enter user email : ");
		email=inp.nextLine();
		System.out.print("Enter user password: ");
		password=inp.nextLine();
		System.out.print("Enter user age : ");
		age=inp.nextInt();
		try {
			tx=em.getTransaction();
		    tx.begin();
		    String query="update User set name=:name,password=:password,age=:age where email=:email";
		    Query q=em.createQuery(query);
		    q.setParameter("name", name);
		    q.setParameter("password", password);
		    q.setParameter("age", age);
		    q.setParameter("email", email);
		    int affectedRow=q.executeUpdate();
		    System.out.println("User Updated Successfully \nTotal User : "+affectedRow);
		    tx.commit();
		}catch(Exception e) {
			System.out.println("Exception during updating user"+e);
		}
	}
	static void readUser(User user,EntityManager em,EntityTransaction tx){
		try {
			String query = "select u from User u";
		 	TypedQuery<User> q = em.createQuery(query,User.class);
		 	List<User> users = q.getResultList();
		 	
		 	String header = String.format("%-20s %-25s %-20s %-5s",
		 	        "Name", "Email", "Password", "Age");
		 	System.out.println("\n----------------------- ALL USER DETAILS ----------------------");
		 	System.out.println("\n==============================================================================================");
		 	System.out.println(header);
		 	System.out.println("================================================================================================\n");
		 	
		 	for(User u : users) {
		 		String row = String.format("%-20s %-25s %-20s %-5d",
			 	        u.getName(), u.getEmail(), u.getPassword(), u.getAge());
		 		System.out.println(row);
		 	}
		 	System.out.println("================================================================================================\n");
		
		}catch(Exception e) {
			System.out.println("Exception during reading user data : "+e);
		}
	}
	static void deleteUser(User user,Scanner inp,EntityManager em,EntityTransaction tx) {
		inp.nextLine();
		System.out.println("<---------- Delete User ---------->");
		System.out.println("Enter user Email");
		String email=inp.nextLine();
		try {
			tx=em.getTransaction();
			tx.begin();
			String query="delete from User where email=:email";
			Query q=em.createQuery(query);
			q.setParameter("email", email);
			
			int affectedRows = q.executeUpdate();
		 	System.out.println("USer deleted successfully : "+affectedRows);
		 	
		 	tx.commit();
		}catch(Exception e) {
			
		}
		
	}
	static void searchUser(User user,Scanner inp,EntityManager em,EntityTransaction tx) {
		inp.nextLine();
		System.out.println("<---------- Search User by their Email ---------->");
		System.out.println("Enter email");
		String userId=inp.nextLine();
		
		try {
			TypedQuery<User> query = em.createNamedQuery("User1.findByEmail", User.class);
		 	query.setParameter("email", userId);
		 	
		 	User users = query.getSingleResult();
		 	System.out.println("\n<------------------ Resultant user ------------>");
			System.out.println("UserId: "+users.getUid());
			System.out.println("Name : "+users.getName());
			System.out.println("Email : "+user.getEmail());
			System.out.println("Password : "+user.getPassword());
			System.out.println("Age : "+user.getAge());
		}catch(Exception e) {
			System.out.println("Exception in searching User");
		}
		
	}
}