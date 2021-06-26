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

import com.esprit.weBank.entities.Account;

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
	

	@OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "credit_fk", referencedColumnName = "id")
	private List<User> credit;

	public Credit(String nomC, int ribR, Double montant, String device,int duree) {
		super();
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


	public String getDevice() {
		return device;
	}


	public void setDevice(String device) {
		this.device = device;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public List<User> getCredit() {
		return credit;
	}


	public void setCredit(List<User> credit) {
		this.credit = credit;
	}


}
