package com.assetmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assetmgmt.dao.TransactionRentDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.TransactionRent;
import com.assetmgmt.entity.model.TransactionRentModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/transactionRent/")
public class TransactionRentController {

	@Autowired
	private TransactionRentDao transactionRentDao;

	@PostMapping("transactionRent")
	public ResponseEntity<ResponseMessage<TransactionRent>> saveUser(@RequestBody TransactionRentModel transactionRentModel) {
		ResponseMessage<TransactionRent> rm = new ResponseMessage<>();

		try {
			TransactionRent transactionRent = transactionRentDao.saveTransactionRent(transactionRentModel);
			if (transactionRent != null) {
				rm.setMessage("TransactionRent Information saved successfully");
				rm.setResults(transactionRent);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionRent Record not saved");
				rm.setResults(transactionRent);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@CrossOrigin
	@PutMapping("transactionRents/{id}")
	public ResponseEntity<ResponseMessage<TransactionRent>> updateUser(@RequestBody TransactionRentModel transactionRentModel,
			@PathVariable Long id) {
		ResponseMessage<TransactionRent> rm = new ResponseMessage<>();

		try {
			System.out.println("TransactionRentId=" + id);
			TransactionRent transactionRent = transactionRentDao.updateTransactionRent(transactionRentModel, id);
			if (transactionRent != null) {
				rm.setMessage("TransactionRent Information saved successfully");
				rm.setResults(transactionRent);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(transactionRent);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping("transactionRents/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<TransactionRent>>> getTransactionRents(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<TransactionRent>> rm = new ResponseMessage<>();

		try {
			List<TransactionRent> transactionRents = transactionRentDao.getAllTransactionRents(limit,offset);
			if (transactionRents != null) {
				rm.setMessage("TransactionRents are available");
				rm.setResults(transactionRents);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionRents are not available.");
				rm.setResults(transactionRents);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping("transactionRents/{id}")
	public ResponseEntity<ResponseMessage<TransactionRent>> getTransactionRentDetail(@PathVariable Long id) {
		ResponseMessage<TransactionRent> rm = new ResponseMessage<>();

		try {
			TransactionRent transactionRents = transactionRentDao.getTransactionRentDetail(id).get(0);
			if (transactionRents != null) {
				rm.setMessage("TransactionRent details are available");
				rm.setResults(transactionRents);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionRent details are not available.");
				rm.setResults(transactionRents);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping("search/{value}")
	public ResponseEntity<ResponseMessage<List<TransactionRent>>> searchTransactionRentDetail(
			@PathVariable String value) {
		ResponseMessage<List<TransactionRent>> rm = new ResponseMessage<>();

		try {
			List<TransactionRent> transactionRents = transactionRentDao.searchTransactionRentInfo(value);
			if (transactionRents != null) {
				rm.setMessage("TransactionRent's are available");
				rm.setResults(transactionRents);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionRent's are not available.");
				rm.setResults(transactionRents);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
