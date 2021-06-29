package com.esprit.weBank.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private Date creationDate;

	@Column(name = "content")
	private String content;
	
	@ManyToOne
	@JoinColumn(name="post_FK", nullable=true)  
	private Post post;
	
	public Comment (){
		
	}



	public Comment(int id, Date creationDate, String content, Post post) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.content = content;
		this.post = post;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
