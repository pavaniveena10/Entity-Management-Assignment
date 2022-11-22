package com.infosys.entitymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityExceptionController {

	@ExceptionHandler(value = InvalidRequestException.class)
	public ResponseEntity<Object> invalidRequestException(InvalidRequestException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = ApplicationRuntimeException.class)
	public ResponseEntity<Object> applicationRunTimeException(ApplicationRuntimeException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
