package com.jpa.main;

import java.util.List;


import com.jpa.entity.Car;
import com.jpa.entity.Truck;
import com.jpa.entity.Vehical;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main{
	public static void main(String args[]) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("unit");
		EntityManager em=emf.createEntityManager();
		
		Car car=new Car();
		car.setVehical_name("Kia");
		car.setNo_of_door(5);
		
		Truck truck=new Truck();
		truck.setContainer(4);
		truck.setVehical_name("Ashok");
		try {
			EntityTransaction tx=em.getTransaction();
			tx.begin();
			em.persist(truck);
			em.persist(car);
			tx.commit();

			List<Vehical> vList = em.createQuery("from Vehical", Vehical.class).getResultList(); 
			for(Vehical v :  vList)
				System.out.println("\nVehicle Id : "+v.getVid()+"\nVehicleName : "+v.getVehical_name()+"\nClass Name : "+v.getClass().getSimpleName());
			
		}catch(Exception e) {
			System.out.println("Exception in main : "+e);
		}
	}
}