package com.infosys.entitymanagement.exception;

public class InvalidRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public InvalidRequestException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
