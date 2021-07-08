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


import com.esprit.weBank.util.AccountType;

@Entity(name = "account")
public class Account{
	
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "account_number")
	private long accNumber;
	@Column(name = "balance")
	private double balance;
	@Column(name = "opening_date")
	private String openingDate;
	@Column(name = "rib")
	private long rib;
	@Column(name = "solde")
	private int solde;
	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType accType;
	@ManyToOne(targetEntity = User.class, optional = false)
	@JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User Owner;

	@OneToOne
	@JoinColumn(name="budget_id")
	private Budget budget;

	public Account() {
		super();
	}

	public Account(long accNumber, double balance, String openingDate, long rib, AccountType accType, int solde) {
		super();
		this.accNumber = accNumber;
		this.balance = balance;
		this.openingDate = openingDate;
		this.rib = rib;
		this.accType = accType;
		this.solde=solde;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(long accNumber) {
		this.accNumber = accNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	public long getRib() {
		return rib;
	}

	public void setRib(long rib) {
		this.rib = rib;
	}

	public AccountType getAccType() {
		return accType;
	}

	public void setAccType(AccountType accType) {
		this.accType = accType;
	}

	public User getOwner() {
		return Owner;
	}

	public void setOwner(User owner) {
		Owner = owner;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}
	
	
}
