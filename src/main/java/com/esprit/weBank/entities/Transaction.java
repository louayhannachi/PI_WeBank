package com.esprit.weBank.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private String dateT;
	@Column(name = "montant")
	private Double montant;
	@Column(name = "devise")
	private String devise;

	@ManyToOne
	@JoinColumn(name = "user_FK", nullable = true)
	private User user;

	public Transaction() {

	}

	public Transaction(int id, String nomC, String type, int ribE, int ribR, String dateT, Double montant, String devise,
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

	public int getUser() {
		return user.getId();
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

	public String getDateT() {
		return dateT;
	}

	public void setDateT(String dateT) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		this.dateT = dtf.format(now);
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

}
