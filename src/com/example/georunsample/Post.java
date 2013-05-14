package com.example.georunsample;

public class Post {
	
	Integer id;
	String title;
	String body;
	
	public Post(Integer id, String title, String body) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getBody() {
		return body;
	}
	
	
}
