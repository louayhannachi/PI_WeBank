package com.esprit.weBank.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	@Column(name = "device")
	private String device;

	@OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "transaction_fk", referencedColumnName = "id")
	private List<User> transaction;

	public Transaction(String nomC, String type, int ribE, int ribR, Date dateT, Double montant, String device) {
		super();
		this.nomC = nomC;
		this.type = type;
		this.ribE = ribE;
		this.ribR = ribR;
		this.dateT = dateT;
		this.montant = montant;
		this.device = device;
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

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public List<User> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<User> transaction) {
		this.transaction = transaction;
	}

}
