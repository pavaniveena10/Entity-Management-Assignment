package com.infosys.entitymanagement.exception;

import lombok.Data;

@Data
public class ErrorResponse {

	private String errorCode;
	private String errorDescription;

	public ErrorResponse(String errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

}
