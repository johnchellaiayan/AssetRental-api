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

import com.assetmgmt.dao.TransactionAccessoriesDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.TransactionAccessories;
import com.assetmgmt.entity.model.TransactionAccessoriesModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/transactionAccessories/")
public class TransactionAccessoriesController {

	@Autowired
	private TransactionAccessoriesDao transactionAccessoriesDao;

	@PostMapping("transactionAccessories")
	public ResponseEntity<ResponseMessage<TransactionAccessories>> saveUser(@RequestBody TransactionAccessoriesModel transactionAccessoriesModel) {
		ResponseMessage<TransactionAccessories> rm = new ResponseMessage<>();

		try {
			TransactionAccessories transactionAccessories = transactionAccessoriesDao.saveTransactionAccessories(transactionAccessoriesModel);
			if (transactionAccessories != null) {
				rm.setMessage("TransactionAccessories Information saved successfully");
				rm.setResults(transactionAccessories);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionAccessories Record not saved");
				rm.setResults(transactionAccessories);
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
	@PutMapping("transactionAccessoriess/{id}")
	public ResponseEntity<ResponseMessage<TransactionAccessories>> updateUser(@RequestBody TransactionAccessoriesModel transactionAccessoriesModel,
			@PathVariable Long id) {
		ResponseMessage<TransactionAccessories> rm = new ResponseMessage<>();

		try {
			System.out.println("TransactionAccessoriesId=" + id);
			TransactionAccessories transactionAccessories = transactionAccessoriesDao.updateTransactionAccessories(transactionAccessoriesModel, id);
			if (transactionAccessories != null) {
				rm.setMessage("TransactionAccessories Information saved successfully");
				rm.setResults(transactionAccessories);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(transactionAccessories);
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

	@GetMapping("transactionAccessoriess/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<TransactionAccessories>>> getTransactionAccessoriess(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<TransactionAccessories>> rm = new ResponseMessage<>();

		try {
			List<TransactionAccessories> transactionAccessoriess = transactionAccessoriesDao.getAllTransactionAccessoriess(limit,offset);
			if (transactionAccessoriess != null) {
				rm.setMessage("TransactionAccessoriess are available");
				rm.setResults(transactionAccessoriess);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionAccessoriess are not available.");
				rm.setResults(transactionAccessoriess);
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

	@GetMapping("transactionAccessoriess/{id}")
	public ResponseEntity<ResponseMessage<TransactionAccessories>> getTransactionAccessoriesDetail(@PathVariable Long id) {
		ResponseMessage<TransactionAccessories> rm = new ResponseMessage<>();

		try {
			TransactionAccessories transactionAccessoriess = transactionAccessoriesDao.getTransactionAccessoriesDetail(id).get(0);
			if (transactionAccessoriess != null) {
				rm.setMessage("TransactionAccessories details are available");
				rm.setResults(transactionAccessoriess);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionAccessories details are not available.");
				rm.setResults(transactionAccessoriess);
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
	public ResponseEntity<ResponseMessage<List<TransactionAccessories>>> searchTransactionAccessoriesDetail(
			@PathVariable String value) {
		ResponseMessage<List<TransactionAccessories>> rm = new ResponseMessage<>();

		try {
			List<TransactionAccessories> transactionAccessoriess = transactionAccessoriesDao.searchTransactionAccessoriesInfo(value);
			if (transactionAccessoriess != null) {
				rm.setMessage("TransactionAccessories's are available");
				rm.setResults(transactionAccessoriess);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionAccessories's are not available.");
				rm.setResults(transactionAccessoriess);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
