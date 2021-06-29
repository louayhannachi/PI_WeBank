package com.esprit.weBank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "depense")

public class Depense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "type")
	private String type;
	@Column(name = "montant")
	private String montant;
	@Column(name = "paiement")
	private String paiement;
	@Column(name = "note")
	private String note;
	
	
	public Depense() {
		super();
	}
	public Depense(int id, String type, String montant, String paiement, String note) {
		super();
		this.id = id;
		this.type = type;
		this.montant = montant;
		this.paiement = paiement;
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	public String getPaiement() {
		return paiement;
	}
	public void setPaiement(String paiement) {
		this.paiement = paiement;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
