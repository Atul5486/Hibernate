package com.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;


@Entity
@Table(name="table_per_class")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehical{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int vid;
	
	@Column(name="vehical_name")
	String vehical_name;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVehical_name() {
		return vehical_name;
	}

	public void setVehical_name(String vehical_name) {
		this.vehical_name = vehical_name;
	}
	
}