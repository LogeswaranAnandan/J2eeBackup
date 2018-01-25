package com.zilker.rssfeed.bean;

import com.zilker.rssfeed.java.Extractor;

public class RssFeedBean {
	private String link;
	private String description;
	private String title;
	private String imageUrl;
	private String category;
	private String publishedDate;
	Extractor extractor = new Extractor();
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPublishedDate() {
		return publishedDate;
	}
	
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String toString() {
		return this.getDescription();
	}
//	public String toString() {
//		return "\nTitle : " + this.getTitle() + "\nLink : " + this.getLink() + "\nCategory : " + this.getCategory() 
//					+ "\nPublished Date : " + this.getPublishedDate();
//	}

	
	
}
