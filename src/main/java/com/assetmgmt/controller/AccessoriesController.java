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

import com.assetmgmt.dao.AccessoriesDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.Accessories;
import com.assetmgmt.entity.model.AccessoriesModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/accessories/")
public class AccessoriesController {

	@Autowired
	private AccessoriesDao accessoriesDao;

	@PostMapping("accessories")
	public ResponseEntity<ResponseMessage<Accessories>> saveUser(@RequestBody AccessoriesModel accessoriesModel) {
		ResponseMessage<Accessories> rm = new ResponseMessage<>();

		try {
			Accessories accessories = accessoriesDao.saveAccessories(accessoriesModel);
			if (accessories != null) {
				rm.setMessage("Accessories Information saved successfully");
				rm.setResults(accessories);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Accessories Record not saved");
				rm.setResults(accessories);
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
	@PutMapping("accessoriess/{id}")
	public ResponseEntity<ResponseMessage<Accessories>> updateUser(@RequestBody AccessoriesModel accessoriesModel,
			@PathVariable Long id) {
		ResponseMessage<Accessories> rm = new ResponseMessage<>();

		try {
			System.out.println("AccessoriesId=" + id);
			Accessories accessories = accessoriesDao.updateAccessories(accessoriesModel, id);
			if (accessories != null) {
				rm.setMessage("Accessories Information saved successfully");
				rm.setResults(accessories);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(accessories);
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

	@GetMapping("accessoriess/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<Accessories>>> getAccessoriess(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<Accessories>> rm = new ResponseMessage<>();

		try {
			List<Accessories> accessoriess = accessoriesDao.getAllAccessoriess(limit,offset);
			if (accessoriess != null) {
				rm.setMessage("Accessoriess are available");
				rm.setResults(accessoriess);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Accessoriess are not available.");
				rm.setResults(accessoriess);
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

	@GetMapping("accessoriess/{id}")
	public ResponseEntity<ResponseMessage<Accessories>> getAccessoriesDetail(@PathVariable Long id) {
		ResponseMessage<Accessories> rm = new ResponseMessage<>();

		try {
			Accessories accessoriess = accessoriesDao.getAccessoriesDetail(id).get(0);
			if (accessoriess != null) {
				rm.setMessage("Accessories details are available");
				rm.setResults(accessoriess);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Accessories details are not available.");
				rm.setResults(accessoriess);
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
	public ResponseEntity<ResponseMessage<List<Accessories>>> searchAccessoriesDetail(
			@PathVariable String value) {
		ResponseMessage<List<Accessories>> rm = new ResponseMessage<>();

		try {
			List<Accessories> accessoriess = accessoriesDao.searchAccessoriesInfo(value);
			if (accessoriess != null) {
				rm.setMessage("Accessories's are available");
				rm.setResults(accessoriess);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Accessories's are not available.");
				rm.setResults(accessoriess);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
