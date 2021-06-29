package com.esprit.weBank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "alert")

public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "alert_date")
	private String alertDate;
	@Column(name = "alert_msg")
	private String alertMsg;
	
	public Alert() {
		super();
	}
	public Alert(int id, String alertDate, String alertMsg) {
		super();
		this.id = id;
		this.alertDate = alertDate;
		this.alertMsg = alertMsg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlertDate() {
		return alertDate;
	}
	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}
	
	
	
	
}
