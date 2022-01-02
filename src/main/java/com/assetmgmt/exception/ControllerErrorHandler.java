package com.assetmgmt.exception;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.context.request.WebRequest;

import com.assetmgmt.dto.ErrorLogDto;
import com.assetmgmt.enumeration.LogOperation;
import com.assetmgmt.util.LogWrapper;

@ControllerAdvice
public class ControllerErrorHandler {


	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleBaseException(DataIntegrityViolationException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value() + "");
		errorResponse.setMessage(e.getRootCause().getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ErrorResponse> handleAll(ConstraintViolationException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value() + "");
		errorResponse.setMessage(ex.getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
		LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.SELECT).errorMessage(ex.getMessage())
						.exception(ex).build());
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value() + "");
		errorResponse.setMessage(ex.getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ BadRequestException.class })
	public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value() + "");
		errorResponse.setMessage(ex.getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ GeneralException.class })
	public ResponseEntity<ErrorResponse> handleInternalRequest(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value() + "");
		errorResponse.setMessage(ex.getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
			 {
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());

		List<String> errors = new ArrayList<>();
		List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
		if (objectErrors != null && objectErrors.size() > 1) {
			objectErrors.forEach(err -> 
				errors.add(err.getDefaultMessage())
			);
		}

		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(WebRequest req, HttpMessageNotReadableException e)
		 {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value() + "");
		errorResponse.setMessage(e.getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseBody
	public final ResponseEntity<ErrorResponse> handleNotFoundException(RecordNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(ex.getCode());
		errorResponse.setMessage(ex.getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(ex.getCode());
		errorResponse.setMessage(ex.getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<ErrorResponse> handleNoResultException(NoResultException ex)  {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value() + "");
		errorResponse.setMessage(ex.getMessage());
		List<String> errors = new ArrayList<>();
		errorResponse.setErrorList(errors);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
