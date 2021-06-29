package com.esprit.weBank.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "post")
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "creationDate")
	private Date creationDate;

	@Column(name = "content")
	private String content;
	
	@OneToMany(mappedBy="post", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();
	/*@OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_comm_fk", referencedColumnName = "id")
	private List<Comment> comments;
	
	@OneToMany(targetEntity = React.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_react_fk", referencedColumnName = "id")
	private List<React> reacts;*/

	public Post(int id, Date creationDate, String content) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.content = content;
	}
	
	public Post(){
		
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

	public List<Comment> getComment() {
		return comments;
	}

	public void setComment(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addcomment(Comment comment){
		comment.setPost(this);
		this.comments.add(comment);
	}
	
	/*public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}*/
	
	

}
