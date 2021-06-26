package com.esprit.weBank.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "comment")
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "creationDate")
	private Date creationDate;

	@Column(name = "content")
	private Date content;

	public Comment(int id, Date creationDate, Date content) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.content = content;
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

	public Date getContent() {
		return content;
	}

	public void setContent(Date content) {
		this.content = content;
	}
	
	
}
