package com.esprit.weBank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "budget")

public class Budget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "plafond")
	private int plafond;
	@Column(name = "limit_date")
	private String limitDate;
	
	
	public Budget() {
		super();
	}
	
	public Budget(int id, int plafond, String limitDate) {
		super();
		this.id = id;
		this.plafond = plafond;
		this.limitDate = limitDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlafond() {
		return plafond;
	}
	public void setPlafond(int plafond) {
		this.plafond = plafond;
	}
	public String getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}
	
	

}
