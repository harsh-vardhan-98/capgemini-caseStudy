package com.hms.payment.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Component
@Document(collection = "paymentDb")
public class PayModel {
	
	@Id
	private String orderId;
	private String cust_id;
	private String amount;
	private String status;
	private String date;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public PayModel(String orderId, String cust_id, String amount, String status, String date) {
		super();
		this.orderId = orderId;
		this.cust_id = cust_id;
		this.amount = amount;
		this.status = status;
		this.date = date;
	}
	public PayModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
