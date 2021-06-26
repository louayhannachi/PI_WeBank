package com.esprit.weBank.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.esprit.weBank.util.AccountType;

@Entity(name = "account")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "account_number")
	private int accNumber;
	@Column(name = "balance")
	private double balance;
	@Column(name = "opening_date")
	private String openingDate;
	@Column(name = "rib")
	private long rib;
	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType accType;
	
	public Account() {
		super();
	}

	public Account(int accNumber, double balance, String openingDate, long rib, AccountType accType) {
		super();
		this.accNumber = accNumber;
		this.balance = balance;
		this.openingDate = openingDate;
		this.rib = rib;
		this.accType = accType;
	}

	public int getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(int accNumber) {
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
	
	
}
