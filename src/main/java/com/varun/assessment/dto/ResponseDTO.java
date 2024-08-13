package com.varun.assessment.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;


@Component
public class ResponseDTO<T> implements Serializable {
	private static final long serialVersionUID = 643344462393528952L;

	@JsonProperty(value = "message")
	private String message;

	@JsonProperty(value = "data")
	private T data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseDTO(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}

	public ResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}