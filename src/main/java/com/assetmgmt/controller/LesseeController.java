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

import com.assetmgmt.dao.LesseeDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.MasterLessee;
import com.assetmgmt.entity.model.LesseeModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/lessee/")
public class LesseeController {

	@Autowired
	private LesseeDao lesseeDao;

	@PostMapping("lessee")
	public ResponseEntity<ResponseMessage<MasterLessee>> saveUser(@RequestBody LesseeModel lesseeModel) {
		ResponseMessage<MasterLessee> rm = new ResponseMessage<>();

		try {
			MasterLessee lessee = lesseeDao.saveLessee(lesseeModel);
			if (lessee != null) {
				rm.setMessage("Lessee Information saved successfully");
				rm.setResults(lessee);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Lessee Record not saved");
				rm.setResults(lessee);
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
	@PutMapping("lessees/{id}")
	public ResponseEntity<ResponseMessage<MasterLessee>> updateUser(@RequestBody LesseeModel lesseeModel,
			@PathVariable Long id) {
		ResponseMessage<MasterLessee> rm = new ResponseMessage<>();

		try {
			System.out.println("LesseeId=" + id);
			MasterLessee lessee = lesseeDao.updateLessee(lesseeModel, id);
			if (lessee != null) {
				rm.setMessage("Lessee Information saved successfully");
				rm.setResults(lessee);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(lessee);
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

	@GetMapping("lessees/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<MasterLessee>>> getLessees(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<MasterLessee>> rm = new ResponseMessage<>();

		try {
			List<MasterLessee> lessees = lesseeDao.getAllLessees(limit,offset);
			if (lessees != null) {
				rm.setMessage("Lessees are available");
				rm.setResults(lessees);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Lessees are not available.");
				rm.setResults(lessees);
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

	@GetMapping("lessees/{id}")
	public ResponseEntity<ResponseMessage<MasterLessee>> getLesseeDetail(@PathVariable Long id) {
		ResponseMessage<MasterLessee> rm = new ResponseMessage<>();

		try {
			MasterLessee lessees = lesseeDao.getLesseeDetail(id).get(0);
			if (lessees != null) {
				rm.setMessage("Lessee details are available");
				rm.setResults(lessees);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Lessee details are not available.");
				rm.setResults(lessees);
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
	public ResponseEntity<ResponseMessage<List<MasterLessee>>> searchLesseeDetail(
			@PathVariable String value) {
		ResponseMessage<List<MasterLessee>> rm = new ResponseMessage<>();

		try {
			List<MasterLessee> lessees = lesseeDao.searchLesseeInfo(value);
			if (lessees != null) {
				rm.setMessage("Lessee's are available");
				rm.setResults(lessees);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Lessee's are not available.");
				rm.setResults(lessees);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
