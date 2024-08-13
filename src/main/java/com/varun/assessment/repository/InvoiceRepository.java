package com.varun.assessment.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.varun.assessment.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Query("SELECT t1 FROM Invoice t1 WHERE t1.status='pending' and t1.due_date < ?1")
	public List<Invoice> getPendingInvoices(LocalDate date);
	
	
}
