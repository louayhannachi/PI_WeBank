package com.esprit.weBank.entities;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.esprit.weBank.entities.Account;;


@Entity(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nomC")
	private String nomC;
	@Column(name = "type")
	private String type;
	@Column(name = "ribE")
	private int ribE;
	@Column(name = "ribR")
	private int ribR;
	@Column(name = "dateT")
	private Date dateT;
	@Column(name = "montant")
	private Double montant;
	@Column(name = "devise")
	private String devise;

	@ManyToOne
	@JoinColumn(name="user_FK", nullable=true)  
	//@NotFound(action = NotFoundAction.IGNORE) 
	private User user;
	/*
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "id", nullable = false)
	
    private User user;
	
	/*@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id_fk", referencedColumnName = "id")
	private Integer user_id_fk;*/



	public Transaction(){
		
	}

	



	public Transaction(int id, String nomC, String type, int ribE, int ribR, Date dateT, Double montant, String devise,
			User user) {
		super();
		this.id = id;
		this.nomC = nomC;
		this.type = type;
		this.ribE = ribE;
		this.ribR = ribR;
		this.dateT = dateT;
		this.montant = montant;
		this.devise = devise;
		this.user = user;
	}





	public String getDevise() {
		return devise;
	}





	public void setDevise(String devise) {
		this.devise = devise;
	}





	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomC() {
		return nomC;
	}

	public void setNomC(String nomC) {
		this.nomC = nomC;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRibE() {
		return ribE;
	}

	public void setRibE(int ribE) {
		this.ribE = ribE;
	}

	public int getRibR() {
		return ribR;
	}

	public void setRibR(int ribR) {
		this.ribR = ribR;
	}

	public Date getDateT() {
		return dateT;
	}

	public void setDateT(Date dateT) {
		this.dateT = dateT;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	



}
