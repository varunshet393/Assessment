 package com.varun.assessment.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.varun.assessment.dto.PaymentRequestDTO;
import com.varun.assessment.dto.ProcessOverdueRequestDTO;
import com.varun.assessment.dto.ResponseDTO;
import com.varun.assessment.model.Invoice;
import com.varun.assessment.service.InvoiceService;

@RestController
public class AssessmentController {
	
	@Autowired
	private InvoiceService invoiceService;
	private static final String SUCCESS = "SUCCESS";
	
	@GetMapping(value = "/get")
	public ResponseEntity<ResponseDTO<String>> getMethod() {
		final ResponseDTO<String> resp = new ResponseDTO<>();
		resp.setData("Hello World");
		resp.setMessage(SUCCESS);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/invoices")
	public ResponseEntity<ResponseDTO<List<Invoice>>> getInvoices() {
		final ResponseDTO<List<Invoice>> resp = new ResponseDTO<>();
		resp.setData(invoiceService.getAllInvoices());
		resp.setMessage(SUCCESS);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/invoices/{id}")
	public ResponseEntity<ResponseDTO<Invoice>> getInvoiceByID(@PathVariable Long id) {
		final ResponseDTO<Invoice> resp = new ResponseDTO<>();
		resp.setData(invoiceService.getInvoiceById(id));
		resp.setMessage(SUCCESS);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PostMapping(value = "/invoices")
    public ResponseEntity<ResponseDTO<String>> createInvoice(@RequestBody Invoice invoice) throws Exception {
        Invoice newInvoice = invoiceService.createInvoice(invoice);
    
        if (newInvoice == null) 
        	throw new Exception("Internal Server error");
        
        
        
        final ResponseDTO<String> resp = new ResponseDTO<>();
		resp.setData(newInvoice.getInvoiceID().toString());
		resp.setMessage(SUCCESS);
		
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
        
	}
	
	
	@PostMapping(value = "/invoices/{id}/payments")
    public ResponseEntity<ResponseDTO<Invoice>> updateInvoice(@PathVariable Long id, @RequestBody PaymentRequestDTO request) throws Exception {
        //Invoice newInvoice = invoiceService.updateInvoice(id, request);
    
      
        final ResponseDTO<Invoice> resp = new ResponseDTO<>();
		resp.setData(invoiceService.updateInvoice(id, request));
		resp.setMessage(SUCCESS);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
        
	}
	
	@PostMapping(value = "/invoices/process-overdue")
    public ResponseEntity<ResponseDTO<List<Invoice>>> processOverdue(@RequestBody ProcessOverdueRequestDTO request) throws Exception {
        //Invoice newInvoice = invoiceService.updateInvoice(id, request);
    
      
        final ResponseDTO<List<Invoice>> resp = new ResponseDTO<>();
		resp.setData(invoiceService.processOverdue(request));
		resp.setMessage(SUCCESS);
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
        
	}

}
