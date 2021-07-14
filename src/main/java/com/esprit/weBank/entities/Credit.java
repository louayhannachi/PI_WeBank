package com.esprit.weBank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "credit")
public class Credit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nomC")
	private String nomC;
	@Column(name = "ribR")
	private int ribR;
	@Column(name = "montant")
	private Double montant;
	@Column(name = "device")
	private String device;
	@Column(name = "duree")
	private int duree;

	@ManyToOne
	@JoinColumn(name = "user_credit_FK", nullable = true)
	private User user;

	public Credit() {

	}

	public Credit(int id, String nomC, int ribR, Double montant, String device, int duree) {
		super();
		this.id = id;
		this.nomC = nomC;
		this.ribR = ribR;
		this.montant = montant;
		this.device = device;
		this.duree = duree;
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

	public int getRibR() {
		return ribR;
	}

	public void setRibR(int ribR) {
		this.ribR = ribR;
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

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getUserID() {
		return user.getId();
	}

	public void setUser(User user) {
		this.user = user;
	}

}
