package com.varun.assessment.dto;

import org.springframework.stereotype.Component;

@Component
public class ProcessOverdueRequestDTO {

	private Double late_fee;
	private int overdue_days;
	public Double getLate_fee() {
		return late_fee;
	}
	public void setLate_fee(Double late_fee) {
		this.late_fee = late_fee;
	}
	public int getOverdue_days() {
		return overdue_days;
	}
	public void setOverdue_days(int overdue_days) {
		this.overdue_days = overdue_days;
	}
	public ProcessOverdueRequestDTO(Double late_fee, int overdue_days) {
		super();
		this.late_fee = late_fee;
		this.overdue_days = overdue_days;
	}
	public ProcessOverdueRequestDTO() {
	}

	
	
}
