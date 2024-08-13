package com.varun.assessment.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.varun.assessment.constants.ResponseCodeConstants;
import com.varun.assessment.exception.DataUpdationException;
import com.varun.assessment.exception.ForbiddenException;
import com.varun.assessment.exception.InvalidArgumentException;
import com.varun.assessment.exception.ResourceAlreadyExistsException;
import com.varun.assessment.exception.ResourceNotFoundException;




@RestControllerAdvice
public class DefaultExceptionHandler {
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<HttpErrorResponse> ForbiddenExcepion(ForbiddenException ex,
			WebRequest request) {
		return ResponseEntity.status(ResponseCodeConstants.FORBIDDEN.getValue())
				.body(new HttpErrorResponse(ResponseCodeConstants.FORBIDDEN.getValue(), new Date(),
						ex.getMessage(), request.getDescription(false)));
	}
	
	@ExceptionHandler(DataUpdationException.class)
	public ResponseEntity<HttpErrorResponse> dataUpdationFailureException(DataUpdationException ex,
			WebRequest request) {
		return ResponseEntity.status(ResponseCodeConstants.DATA_UPDATION_FAIL.getValue())
				.body(new HttpErrorResponse(ResponseCodeConstants.DATA_UPDATION_FAIL.getValue(), new Date(),
						ex.getMessage(), request.getDescription(false)));
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public HttpErrorResponse globalExceptionHandler(Exception ex, WebRequest request) {
		return new HttpErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(InvalidArgumentException.class)
	public ResponseEntity<HttpErrorResponse> InvalidArgumentException(InvalidArgumentException ex,
			WebRequest request) {
		return ResponseEntity.status(ResponseCodeConstants.INVALID_ARGUMENT.getValue())
				.body(new HttpErrorResponse(ResponseCodeConstants.INVALID_ARGUMENT.getValue(), new Date(), ex.getMessage(),
						request.getDescription(false)));
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<HttpErrorResponse> ResourceAlreadyException(ResourceAlreadyExistsException ex,
			WebRequest request) {
		return ResponseEntity.status(ResponseCodeConstants.RESOURCE_ALREADY_EXISTS.getValue())
				.body(new HttpErrorResponse(ResponseCodeConstants.RESOURCE_ALREADY_EXISTS.getValue(), new Date(), ex.getMessage(),
						request.getDescription(false)));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<HttpErrorResponse> ResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		return ResponseEntity.status(ResponseCodeConstants.RESOURCE_NOT_FOUND.getValue())
				.body(new HttpErrorResponse(ResponseCodeConstants.RESOURCE_NOT_FOUND.getValue(), new Date(), ex.getMessage(),
						request.getDescription(false)));
	}
	
	

}
