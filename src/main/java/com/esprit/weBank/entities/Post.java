package com.esprit.weBank.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "creationDate")
	private String creationDate;

	@Column(name = "content")
	private String content;

	@Column(name = "updatedDate")
	private String updatedDate;

	@Column(name = "nbrLikes")
	private int nbrLikes;

	@Column(name = "nbrDislikes")
	private int nbrDislikes;

	@OneToMany(mappedBy = "post", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "post", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<React> reacts;
	
	@ManyToOne
	@JoinColumn(name = "user_FK", nullable = true)
	private User user;


	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name="user_FK", nullable=true) private User user;
	 */
	/*
	 * @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "post_comm_fk", referencedColumnName = "id") private
	 * List<Comment> comments;
	 * 
	 * @OneToMany(targetEntity = React.class, cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "post_react_fk", referencedColumnName = "id") private
	 * List<React> reacts;
	 */

	public Post(int id, String creationDate, String content, String updatedDate, int nbrLikes, int nbrDislikes, User user) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.content = content;
		this.updatedDate = updatedDate;
		this.nbrLikes = nbrLikes;
		this.nbrDislikes = nbrDislikes;
		this.user = user;


	}

	public Post() {

	}

	public int getId() {
		return id;
	}

	/*public int getUser() {
		return user.getId();
	}*/

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
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

	public int getNbrLikes() {
		return nbrLikes;
	}

	public void setNbrLikes(int nbrLikes) {
		this.nbrLikes = nbrLikes;
	}

	public int getNbrDislikes() {
		return nbrDislikes;
	}

	public void setNbrDislikes(int nbrDislikes) {
		this.nbrDislikes = nbrDislikes;
	}

	// *************comment Join**************

	public Set<Comment> getComment() {
		return comments;
	}

	public void setComment(Set<Comment> comments) {
		this.comments = comments;
	}

	public void addcomment(Comment comment) {
		comment.setPost(this);
		this.comments.add(comment);
	}

	// *************comment Join**************

	/*
	 * public Set<React> getReact() { return reacts; }
	 */

	public void setReact(Set<React> reacts) {
		this.reacts = reacts;
	}

	public void addreact(React react) {
		react.setPost(this);
		this.reacts.add(react);
	}

	// ******************user*****************//

}
