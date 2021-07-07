package com.esprit.weBank.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "comment")
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "creationDate")
	private String creationDate;

	@Column(name = "content")
	private String content;
	
	@Column(name = "updatedDate")
	private String updatedDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="post_FK", nullable=true)  
	private Post post;
	
	public Comment (){
		
	}
	

	public Comment(int id, String creationDate, String content, String updatedDate, Post post) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.content = content;
		this.updatedDate = updatedDate;
		this.post = post;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		this.creationDate = dtf.format(now);
	}

	
	public String getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(String updatedDate) {
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		this.updatedDate = dtf.format(now);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public int getPost() {
		return post.getId();
	}



	public void setPost(Post post) {
		this.post = post;
	}
	
	
	
}
