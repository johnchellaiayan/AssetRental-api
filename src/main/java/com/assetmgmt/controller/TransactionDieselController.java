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

import com.assetmgmt.dao.TransactionDieselDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.TransactionDiesel;
import com.assetmgmt.entity.model.TransactionDieselModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/transactionDiesel/")
public class TransactionDieselController {

	@Autowired
	private TransactionDieselDao transactionDieselDao;

	@PostMapping("transactionDiesel")
	public ResponseEntity<ResponseMessage<TransactionDiesel>> saveUser(@RequestBody TransactionDieselModel transactionDieselModel) {
		ResponseMessage<TransactionDiesel> rm = new ResponseMessage<>();

		try {
			TransactionDiesel transactionDiesel = transactionDieselDao.saveTransactionDiesel(transactionDieselModel);
			if (transactionDiesel != null) {
				rm.setMessage("TransactionDiesel Information saved successfully");
				rm.setResults(transactionDiesel);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionDiesel Record not saved");
				rm.setResults(transactionDiesel);
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
	@PutMapping("transactionDiesels/{id}")
	public ResponseEntity<ResponseMessage<TransactionDiesel>> updateUser(@RequestBody TransactionDieselModel transactionDieselModel,
			@PathVariable Long id) {
		ResponseMessage<TransactionDiesel> rm = new ResponseMessage<>();

		try {
			System.out.println("TransactionDieselId=" + id);
			TransactionDiesel transactionDiesel = transactionDieselDao.updateTransactionDiesel(transactionDieselModel, id);
			if (transactionDiesel != null) {
				rm.setMessage("TransactionDiesel Information saved successfully");
				rm.setResults(transactionDiesel);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(transactionDiesel);
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

	@GetMapping("transactionDiesels/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<TransactionDiesel>>> getTransactionDiesels(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<TransactionDiesel>> rm = new ResponseMessage<>();

		try {
			List<TransactionDiesel> transactionDiesels = transactionDieselDao.getAllTransactionDiesels(limit,offset);
			if (transactionDiesels != null) {
				rm.setMessage("TransactionDiesels are available");
				rm.setResults(transactionDiesels);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionDiesels are not available.");
				rm.setResults(transactionDiesels);
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

	@GetMapping("transactionDiesels/{id}")
	public ResponseEntity<ResponseMessage<TransactionDiesel>> getTransactionDieselDetail(@PathVariable Long id) {
		ResponseMessage<TransactionDiesel> rm = new ResponseMessage<>();

		try {
			TransactionDiesel transactionDiesels = transactionDieselDao.getTransactionDieselDetail(id).get(0);
			if (transactionDiesels != null) {
				rm.setMessage("TransactionDiesel details are available");
				rm.setResults(transactionDiesels);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionDiesel details are not available.");
				rm.setResults(transactionDiesels);
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
	public ResponseEntity<ResponseMessage<List<TransactionDiesel>>> searchTransactionDieselDetail(
			@PathVariable String value) {
		ResponseMessage<List<TransactionDiesel>> rm = new ResponseMessage<>();

		try {
			List<TransactionDiesel> transactionDiesels = transactionDieselDao.searchTransactionDieselInfo(value);
			if (transactionDiesels != null) {
				rm.setMessage("TransactionDiesel's are available");
				rm.setResults(transactionDiesels);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("TransactionDiesel's are not available.");
				rm.setResults(transactionDiesels);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
