package com.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Car extends Vehical{
	
	@Column(name="no_of_door")
	int no_of_door;

	public int getNo_of_door() {
		return no_of_door;
	}

	public void setNo_of_door(int no_of_door) {
		this.no_of_door = no_of_door;
	}
}