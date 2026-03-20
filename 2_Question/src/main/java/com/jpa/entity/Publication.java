package com.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "publication")
@Inheritance(strategy = InheritanceType.JOINED) // Specifies the JOINED strategy
public abstract class Publication {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
     
}
