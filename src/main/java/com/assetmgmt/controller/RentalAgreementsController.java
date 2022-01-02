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

import com.assetmgmt.dao.RentalAgreementsDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.RentalAgreements;
import com.assetmgmt.entity.model.RentalAgreementsModel;

@RestController
@RequestMapping("api/rentalAgreements/")
public class RentalAgreementsController {

	@Autowired
	private RentalAgreementsDao rentalAgreementsDao;

	@PostMapping("rentalAgreementss")
	public ResponseEntity<ResponseMessage<RentalAgreements>> saveUser(@RequestBody RentalAgreementsModel rentalAgreementsModel) {
		ResponseMessage<RentalAgreements> rm = new ResponseMessage<>();

		try {
			RentalAgreements rentalAgreements = rentalAgreementsDao.saveRentalAgreements(rentalAgreementsModel);
			if (rentalAgreements != null) {
				rm.setMessage("RentalAgreements Information saved successfully");
				rm.setResults(rentalAgreements);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(rentalAgreements);
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
	@PutMapping("rentalAgreementss/{id}")
	public ResponseEntity<ResponseMessage<RentalAgreements>> updateUser(@RequestBody RentalAgreementsModel rentalAgreementsModel,
			@PathVariable Long id) {
		ResponseMessage<RentalAgreements> rm = new ResponseMessage<>();

		try {
			RentalAgreements rentalAgreements = rentalAgreementsDao.updateRentalAgreements(rentalAgreementsModel,id);
			if (rentalAgreements != null) {
				rm.setMessage("RentalAgreements Information saved successfully");
				rm.setResults(rentalAgreements);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(rentalAgreements);
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
	
	@GetMapping("rentalAgreementss/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<RentalAgreements>>> getRentalAgreementss(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<RentalAgreements>> rm = new ResponseMessage<>();

		try {
			List<RentalAgreements> rentalAgreementss = rentalAgreementsDao.getAllRentalAgreementss(limit,offset);
			if (rentalAgreementss != null) {
				rm.setMessage("RentalAgreementss are available");
				rm.setResults(rentalAgreementss);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("RentalAgreementss are not available.");
				rm.setResults(rentalAgreementss);
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
	@GetMapping("rentalAgreementss/{id}")
	public ResponseEntity<ResponseMessage<RentalAgreements>> getRentalAgreementsDetail(@PathVariable Long id) {
		ResponseMessage<RentalAgreements> rm = new ResponseMessage<>();

		try {
			RentalAgreements rentalAgreements = rentalAgreementsDao.getRentalAgreementsDetail(id).get(0);
			if (rentalAgreements != null) {
				rm.setMessage("RentalAgreements details are available");
				rm.setResults(rentalAgreements);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("RentalAgreements details are not available.");
				rm.setResults(rentalAgreements);
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
	
	@GetMapping("search/{field}/{value}")
	public ResponseEntity<ResponseMessage<List<RentalAgreements>>> searchRentalAgreementsDetail(@PathVariable String field, 
			@PathVariable String value) {
		ResponseMessage<List<RentalAgreements>> rm = new ResponseMessage<>();

		try {
			List<RentalAgreements> rentalAgreementss = rentalAgreementsDao.searchRentalAgreementsInfo(field, value);
			if (rentalAgreementss != null) {
				rm.setMessage("RentalAgreementss are available");
				rm.setResults(rentalAgreementss);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("RentalAgreementss are not available.");
				rm.setResults(rentalAgreementss);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping("search/activerentalAgreementss/{value}")
	public ResponseEntity<ResponseMessage<List<RentalAgreements>>> getActiveRentalAgreementss(@PathVariable String value) {
		ResponseMessage<List<RentalAgreements>> rm = new ResponseMessage<>();

		try {
			List<RentalAgreements> rentalAgreementss = rentalAgreementsDao.getActiveRentalAgreementss(value);
			if (rentalAgreementss != null) {
				rm.setMessage("Active RentalAgreementss are available");
				rm.setResults(rentalAgreementss);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Active RentalAgreementss are not available.");
				rm.setResults(rentalAgreementss);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping("getLiscenseExpiredRentalAgreementss")
	public ResponseEntity<ResponseMessage<List<RentalAgreements>>> getLiscenseExpiredRentalAgreementss() {
		ResponseMessage<List<RentalAgreements>> rm = new ResponseMessage<>();

		try {
			List<RentalAgreements> rentalAgreementss = rentalAgreementsDao.getLiscenseExpiredRentalAgreementss();
			if (rentalAgreementss != null) {
				rm.setMessage("RentalAgreementss are available");
				rm.setResults(rentalAgreementss);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("RentalAgreementss are not available.");
				rm.setResults(rentalAgreementss);
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
}
