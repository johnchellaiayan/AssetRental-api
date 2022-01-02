package com.assetmgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException {
	
	private final String code;

    public GeneralException(String message) {
        super(message);
        this.code="";
    }
    
    public GeneralException(String message, String code) {
		super(message);
		this.code = code;
	}
}
