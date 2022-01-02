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

import com.assetmgmt.dao.AccessoriesAmcDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.AccessoriesAmc;
import com.assetmgmt.entity.model.AccessoriesAmcModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/accessoriesAmc/")
public class AccessoriesAmcController {

	@Autowired
	private AccessoriesAmcDao accessoriesAmcDao;

	@PostMapping("accessoriesAmc")
	public ResponseEntity<ResponseMessage<AccessoriesAmc>> saveUser(@RequestBody AccessoriesAmcModel accessoriesAmcModel) {
		ResponseMessage<AccessoriesAmc> rm = new ResponseMessage<>();

		try {
			AccessoriesAmc accessoriesAmc = accessoriesAmcDao.saveAccessoriesAmc(accessoriesAmcModel);
			if (accessoriesAmc != null) {
				rm.setMessage("AccessoriesAmc Information saved successfully");
				rm.setResults(accessoriesAmc);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("AccessoriesAmc Record not saved");
				rm.setResults(accessoriesAmc);
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
	@PutMapping("accessoriesAmcs/{id}")
	public ResponseEntity<ResponseMessage<AccessoriesAmc>> updateUser(@RequestBody AccessoriesAmcModel accessoriesAmcModel,
			@PathVariable Long id) {
		ResponseMessage<AccessoriesAmc> rm = new ResponseMessage<>();

		try {
			System.out.println("AccessoriesAmcId=" + id);
			AccessoriesAmc accessoriesAmc = accessoriesAmcDao.updateAccessoriesAmc(accessoriesAmcModel, id);
			if (accessoriesAmc != null) {
				rm.setMessage("AccessoriesAmc Information saved successfully");
				rm.setResults(accessoriesAmc);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(accessoriesAmc);
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

	@GetMapping("accessoriesAmcs/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<AccessoriesAmc>>> getAccessoriesAmcs(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<AccessoriesAmc>> rm = new ResponseMessage<>();

		try {
			List<AccessoriesAmc> accessoriesAmcs = accessoriesAmcDao.getAllAccessoriesAmcs(limit,offset);
			if (accessoriesAmcs != null) {
				rm.setMessage("AccessoriesAmcs are available");
				rm.setResults(accessoriesAmcs);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("AccessoriesAmcs are not available.");
				rm.setResults(accessoriesAmcs);
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

	@GetMapping("accessoriesAmcs/{id}")
	public ResponseEntity<ResponseMessage<AccessoriesAmc>> getAccessoriesAmcDetail(@PathVariable Long id) {
		ResponseMessage<AccessoriesAmc> rm = new ResponseMessage<>();

		try {
			AccessoriesAmc accessoriesAmcs = accessoriesAmcDao.getAccessoriesAmcDetail(id).get(0);
			if (accessoriesAmcs != null) {
				rm.setMessage("AccessoriesAmc details are available");
				rm.setResults(accessoriesAmcs);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("AccessoriesAmc details are not available.");
				rm.setResults(accessoriesAmcs);
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
	public ResponseEntity<ResponseMessage<List<AccessoriesAmc>>> searchAccessoriesAmcDetail(
			@PathVariable String value) {
		ResponseMessage<List<AccessoriesAmc>> rm = new ResponseMessage<>();

		try {
			List<AccessoriesAmc> accessoriesAmcs = accessoriesAmcDao.searchAccessoriesAmcInfo(value);
			if (accessoriesAmcs != null) {
				rm.setMessage("AccessoriesAmc's are available");
				rm.setResults(accessoriesAmcs);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("AccessoriesAmc's are not available.");
				rm.setResults(accessoriesAmcs);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
