package com.varun.assessment.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varun.assessment.dto.PaymentRequestDTO;
import com.varun.assessment.dto.ProcessOverdueRequestDTO;
import com.varun.assessment.exception.ResourceNotFoundException;
import com.varun.assessment.model.Invoice;
import com.varun.assessment.repository.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	public List<Invoice> getAllInvoices() {
		return invoiceRepository.findAll();
	}

	public Invoice getInvoiceById(Long id) {
		return invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
	}

	public Invoice createInvoice(Invoice invoice) {
		// Date date =
		if (invoice != null) {
			invoice.setPaidAmount(0.0);
			invoice.setStatus("pending");
			invoice.setCreatedat(new Date());
		}
		return invoiceRepository.save(invoice);
	}

	public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
		invoice.setPaidAmount(invoiceDetails.getPaidAmount());
		// invoice.setEmail(invoiceDetails.getEmail());
		return invoiceRepository.save(invoice);
	}

	public void deleteInvoice(Long id) {
		invoiceRepository.deleteById(id);
	}

	public Invoice updateInvoice(Long id, PaymentRequestDTO request) {
		
		Invoice invoice = getInvoiceById(id);
		invoice.setPaidAmount(invoice.getPaidAmount() + request.getAmount());

		if (invoice.getAmount() <= invoice.getPaidAmount()) {
			invoice.setStatus("paid");
			invoice.setPaidAt(new Date());
		}

		return invoiceRepository.save(invoice);
	}

	public List<Invoice> processOverdue(ProcessOverdueRequestDTO request) {
		List<Invoice> ret = new ArrayList<>();
		List<Invoice> pendingInvoices =  invoiceRepository.getPendingInvoices(LocalDate.now());
		
		for(Invoice invoice : pendingInvoices) {
			ret.add(invoice);
			
			if (invoice.getPaidAmount() > 0 ) {
				invoice.setStatus("paid");
				invoice.setPaidAt(new Date());
			}
			if (invoice.getPaidAmount() == 0.0) {
				invoice.setStatus("void");
			}
				
			Invoice newInvoice = new Invoice();
				newInvoice.setAmount(invoice.getAmount() - invoice.getPaidAmount() + request.getLate_fee());
				newInvoice.setPaidAmount(0.0);
				newInvoice.setStatus("pending");
				newInvoice.setCreatedat(new Date());
				newInvoice.setDue_date(LocalDate.now().plusDays(request.getOverdue_days()));
				
				
			ret.add(newInvoice);
		}
		
		return invoiceRepository.saveAll(ret);
	
		
	}

}
