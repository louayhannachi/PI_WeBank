package com.esprit.weBank.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.esprit.weBank.util.InvestmentRisk;
import com.esprit.weBank.util.InvestmentType;

@Entity(name = "investment")
public class Investment{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "investment_name")
	private String investName;
	
	@Column(name = "investment_type")
	@Enumerated(EnumType.STRING)
	private InvestmentType investType;
	
	@Column(name ="investment_risk")
	@Enumerated(EnumType.STRING)
	private InvestmentRisk investRisk;
	
	@Column(name = "investment_cost")
	private double investCost;
	
	@Column(name = "investment_year")
	private String investYear;
	
	@Column(name = "roi")
	private long roi;
	
	@ManyToOne(targetEntity = Account.class, optional = false)
	@JoinColumn(name = "account_number", nullable = false)
	private Account accNumber;

	public Investment() {
		super();
	}

	public Investment(String investName, InvestmentType investType, InvestmentRisk investRisk, double investCost, String investYear, long roi) {
		super();
		this.investName = investName;
		this.investType = investType;
		this.investRisk = investRisk;
		this.investCost = investCost;
		this.investYear = investYear;
		this.roi = roi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getInvestName() {
		return investName;
	}

	public void setInvestName(String investName) {
		this.investName = investName;
	}

	public InvestmentType getInvestType() {
		return investType;
	}

	public void setInvestType(InvestmentType investType) {
		this.investType = investType;
	}
	
	public InvestmentRisk getInvestRisk() {
		return investRisk;
	}

	public void setInvestRisk(InvestmentRisk investRisk) {
		this.investRisk = investRisk;
	}

	public double getInvestCost() {
		return investCost;
	}

	public void setInvestCost(double investCost) {
		this.investCost = investCost;
	}

	public String getInvestYear() {
		return investYear;
	}

	public void setInvestYear(String investYear) {
		this.investYear = investYear;
	}

	public long getRoi() {
		return roi;
	}

	public void setRoi(long roi) {
		this.roi = roi;
	}
	
	public Account getAccount() {
		return accNumber;
	}

	public void setAccount(Account accNumber) {
		this.accNumber = accNumber;
	}
	
}
