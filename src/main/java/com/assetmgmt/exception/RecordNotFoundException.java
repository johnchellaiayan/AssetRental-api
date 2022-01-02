package com.assetmgmt.exception;

import lombok.Data;

//@ResponseStatus(HttpStatus.CONFLICT)
@Data
public class RecordNotFoundException extends RuntimeException {
	private final String code;
	private static final String EXCEPTION = "RecordNotFoundException";
	private static final String CLASSNAME = "com.tw";
	public RecordNotFoundException(String message) {
		super(message);
		this.code="404";
	}

	public RecordNotFoundException(String message, String code) {
		super(message);
		this.code=code;
	}
 
 

 
}
