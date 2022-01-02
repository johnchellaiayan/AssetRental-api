package com.assetmgmt.exception;

import java.util.List;

import lombok.Data;

@Data
public class CustomErrorResponse extends RuntimeException {

	private final Integer errorCode;
	// General error message about nature of error
	private final String message;
	// Specific errors in API request processing
	private final List<String> errorList;

	public CustomErrorResponse() {
		this.errorCode = null;
		this.message = null;
		this.errorList = null;
	}

	public CustomErrorResponse(Integer errorCode, String message, List<String> errorList) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.errorList = errorList;
	}

	public CustomErrorResponse(Integer errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.errorList = null;
	}
}
