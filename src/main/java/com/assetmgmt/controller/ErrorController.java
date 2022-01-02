package com.assetmgmt.controller;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assetmgmt.dto.ErrorLogDto;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.ApplicationError;
import com.assetmgmt.enumeration.LogOperation;
import com.assetmgmt.repository.ErrorLogRepository;
import com.assetmgmt.util.LogWrapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/public/errors")
public class ErrorController {

	@Autowired
	private ErrorLogRepository repo;

	@Transactional
	@PostMapping(value = "")
	@ApiOperation(value = "save  error logs")
	public ResponseEntity<ResponseMessage<String>> saveError(@RequestBody ApplicationError applicationError) {
		ResponseMessage<String> message = new ResponseMessage<>();

		try {
			applicationError.setCreatedAt(new Date());
			applicationError = repo.save(applicationError);
			if (applicationError != null) {
				message.setMessage("error logs saved successfully");
				message.setResults(null);
				message.setStatusCode(1);

			} else {
				message.setMessage("error log not saved");
				message.setResults(null);
				message.setStatusCode(0);

			}
		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder()
					.operation(LogOperation.SELECT).errorMessage(e.getMessage()).exception(e).build());
			throw e;
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
