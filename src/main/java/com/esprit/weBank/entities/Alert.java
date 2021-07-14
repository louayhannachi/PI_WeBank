package com.esprit.weBank.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "alert")

public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "alert_date")
	private Date alertDate;
	@Column(name = "alert_msg")
	private String alertMsg;
	
	public Alert() {
		super();
	}
	public Alert(int id, Date alertDate, String alertMsg) {
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
	public Date getAlertDate() {
		return alertDate;
	}
	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}
	public String getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	
}
