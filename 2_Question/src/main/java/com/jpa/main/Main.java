package com.jpa.main;


import com.jpa.entity.Blog;
import com.jpa.entity.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main{
	public static void main(String args[]) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("unit");
		
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Book book=new Book();
		book.setTitle("Last Five mid night");
		book.setAuthor("James");
		book.setPages(69);
		
		Blog blog=new Blog();
		blog.setTitle("The harlious Night");
		blog.setUrl("https://myblog.com");
		
		try {
			em.persist(blog);
			em.persist(book);
			tx.commit();
			System.out.println("Data Inserted Successfully");
		}catch(Exception e) {
			System.out.println("Exception occur in Main : "+e);
		}
	}
}