package com.varun.assessment;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.varun.assessment.dto.LocalDateTypeAdapter;
import com.varun.assessment.dto.PaymentRequestDTO;
import com.varun.assessment.model.Invoice;

@SpringBootTest(classes = { AssessmentApplication.class })
@AutoConfigureMockMvc
class AssessmentApplicationTests extends AbstractTestNGSpringContextTests {
	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetInvoice() throws Exception {
		// List<Invoice> testInvoices = getTestInvoices(3, false);

		mockMvc.perform(MockMvcRequestBuilders.get("/invoices").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andDo(print());

	}

	@Test
	public void testAddInvoice() throws Exception {
		List<Invoice> testInvoices = getTestInvoices(3, false);

		for (Invoice invoice : testInvoices) {

			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();

			final String json = gson.toJson(invoice);
			mockMvc.perform(
					MockMvcRequestBuilders.post("/invoices").contentType(MediaType.APPLICATION_JSON).content(json))
					.andExpect(status().isCreated()).andDo(print());
		}
	}

	@Test
	public void testUpdateInvoice() throws Exception {
		testAddInvoice();
		PaymentRequestDTO payReq = new PaymentRequestDTO();
		payReq.setAmount(1000.00);

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();

		final String json = gson.toJson(payReq);
		mockMvc.perform(MockMvcRequestBuilders.post("/invoices/1/payments").contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isOk()).andDo(print());

	}

	private List<Invoice> getTestInvoices(int totalInvoices, boolean expired) {
		// TODO Auto-generated method stub
		List<Invoice> testInvoices = new ArrayList<>();
		int i = 0;
		while (i < totalInvoices) {
			Invoice invoice = new Invoice();
			invoice.setAmount(2000.00);
			// invoice.setCreatedat(new Date());
			if (!expired)
				invoice.setDue_date(LocalDate.now().plusDays(5));
			else
				invoice.setDue_date(LocalDate.now().minusDays(2));

			testInvoices.add(invoice);

			i++;

		}
		return testInvoices;
	}

}
