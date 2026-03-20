package com.jpa.entity;

import jakarta.persistence.Entity;

@Entity(name = "BOOK")
public class Book extends Publication {
	
    private int pages;
    
    
    private String author;

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
    
    
}
