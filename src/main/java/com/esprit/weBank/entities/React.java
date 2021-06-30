package com.esprit.weBank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.esprit.weBank.util.ReactType;

@Entity(name = "react")
public class React {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "reactType")
	@Enumerated(EnumType.STRING)
	private ReactType reactType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="post_FK", nullable=true)  
	private Post post;

	public React(){
	}
	
	public React(int id , Post post) {
		super();
		this.id = id;
		this.post = post;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ReactType getReactType() {
		return reactType;
	}

	public void setReactType(ReactType reactType) {
		this.reactType = reactType;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	

	
}
