package com.varun.assessment.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long invoiceID;
	private Double amount;
	private Double paidAmount;
	private LocalDate due_date;
	private String status;
	private Date createdat;
	private Date paidAt;
	public Long getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(Long invoiceID) {
		this.invoiceID = invoiceID; 
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public LocalDate getDue_date() {
		return due_date;
	}
	public void setDue_date(LocalDate dueDate) {
		this.due_date = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public Date getPaidAt() {
		return paidAt;
	}
	public void setPaidAt(Date paidAt) {
		this.paidAt = paidAt;
	}
	@Override
	public String toString() {
		return "Invoice [invoiceID=" + invoiceID + ", invoiceAmount=" + amount + ", paidAmount=" + paidAmount
				+ ", due_date=" + due_date + ", status=" + status + ", createdat=" + createdat + ", paidAt=" + paidAt
				+ "]";
	}
	
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
