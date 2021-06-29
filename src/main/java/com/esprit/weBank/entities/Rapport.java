package com.esprit.weBank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "rapport")

public class Rapport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "begin_date")
	private String beginDate;
	@Column(name = "end_date")
	private String endDate;
	@Column(name = "critere")
	private String critere;
	
	public Rapport() {
		super();
	}
	public Rapport(int id, String beginDate, String endDate, String critere) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.critere = critere;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCritere() {
		return critere;
	}
	public void setCritere(String critere) {
		this.critere = critere;
	}
	
	
	
	
}
