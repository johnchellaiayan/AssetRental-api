package com.assetmgmt.exception;

import java.util.List;

import lombok.Data;
 
@Data
public class ErrorResponse {
	
	public ErrorResponse()
	{
		
	}

	private String errorCode;
    //General error message about nature of error
    private String message=null;
     //Specific errors in API request processing
    private List<String> errorList=null;  
    

	public ErrorResponse(String errorCode, String message, List<String> errorList) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.errorList = errorList;
	}
	public ErrorResponse(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}	
}
