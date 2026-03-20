package com.jpa.entity;
import jakarta.persistence.Entity;

@Entity(name = "blog")
public class Blog extends Publication {

    private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
    
   
}
