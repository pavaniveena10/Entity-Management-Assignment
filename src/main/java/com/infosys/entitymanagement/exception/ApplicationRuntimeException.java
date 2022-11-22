package com.infosys.entitymanagement.exception;

public class ApplicationRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public ApplicationRuntimeException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
