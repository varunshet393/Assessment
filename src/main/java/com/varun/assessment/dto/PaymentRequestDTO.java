package com.varun.assessment.dto;

import org.springframework.stereotype.Component;

@Component
public class PaymentRequestDTO {

	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentRequestDTO() {
		
	}

	public PaymentRequestDTO(Double amount) {
		super();
		this.amount = amount;
	}
	
	
}
