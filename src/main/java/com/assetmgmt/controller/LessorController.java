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

import com.assetmgmt.dao.LessorDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.MasterLessor;
import com.assetmgmt.entity.model.LessorModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/lessor/")
public class LessorController {

	@Autowired
	private LessorDao lessorDao;

	@PostMapping("lessor")
	public ResponseEntity<ResponseMessage<MasterLessor>> saveUser(@RequestBody LessorModel lessorModel) {
		ResponseMessage<MasterLessor> rm = new ResponseMessage<>();

		try {
			MasterLessor lessor = lessorDao.saveLessor(lessorModel);
			if (lessor != null) {
				rm.setMessage("Lessor Information saved successfully");
				rm.setResults(lessor);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Lessor Record not saved");
				rm.setResults(lessor);
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
	@PutMapping("lessors/{id}")
	public ResponseEntity<ResponseMessage<MasterLessor>> updateUser(@RequestBody LessorModel lessorModel,
			@PathVariable Long id) {
		ResponseMessage<MasterLessor> rm = new ResponseMessage<>();

		try {
			System.out.println("LessorId=" + id);
			MasterLessor lessor = lessorDao.updateLessor(lessorModel, id);
			if (lessor != null) {
				rm.setMessage("Lessor Information saved successfully");
				rm.setResults(lessor);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(lessor);
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

	@GetMapping("lessors/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<MasterLessor>>> getLessors(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<MasterLessor>> rm = new ResponseMessage<>();

		try {
			List<MasterLessor> lessors = lessorDao.getAllLessors(limit,offset);
			if (lessors != null) {
				rm.setMessage("Lessors are available");
				rm.setResults(lessors);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Lessors are not available.");
				rm.setResults(lessors);
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

	@GetMapping("lessors/{id}")
	public ResponseEntity<ResponseMessage<MasterLessor>> getLessorDetail(@PathVariable Long id) {
		ResponseMessage<MasterLessor> rm = new ResponseMessage<>();

		try {
			MasterLessor lessors = lessorDao.getLessorDetail(id).get(0);
			if (lessors != null) {
				rm.setMessage("Lessor details are available");
				rm.setResults(lessors);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Lessor details are not available.");
				rm.setResults(lessors);
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
	public ResponseEntity<ResponseMessage<List<MasterLessor>>> searchLessorDetail(
			@PathVariable String value) {
		ResponseMessage<List<MasterLessor>> rm = new ResponseMessage<>();

		try {
			List<MasterLessor> lessors = lessorDao.searchLessorInfo(value);
			if (lessors != null) {
				rm.setMessage("Lessors are available");
				rm.setResults(lessors);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Lessors are not available.");
				rm.setResults(lessors);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
