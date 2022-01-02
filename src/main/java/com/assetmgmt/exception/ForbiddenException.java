package com.assetmgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@Data
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	private final String code;

	public ForbiddenException(String message) {
		super(message);
		this.code = "403";
	}

	public ForbiddenException(String message, String code) {
		super(message);
		this.code = code;
	}

}
