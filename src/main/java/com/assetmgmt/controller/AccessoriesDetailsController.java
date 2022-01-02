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

import com.assetmgmt.dao.AccessoriesDetailsDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.AccessoriesDetails;
import com.assetmgmt.entity.model.AccessoriesDetailsModel;

@RestController
@RequestMapping("api/accessoriesdetails/")
public class AccessoriesDetailsController {

	@Autowired
	private AccessoriesDetailsDao bookingDao;

	@PostMapping("bookings")
	public ResponseEntity<ResponseMessage<AccessoriesDetails>> saveAccessoriesDetails(
			@RequestBody AccessoriesDetailsModel bookingModel) {
		ResponseMessage<AccessoriesDetails> rm = new ResponseMessage<>();

		try {
			AccessoriesDetails booking = bookingDao.saveAccessoriesDetails(bookingModel);
			if (booking != null) {
				rm.setMessage("AccessoriesDetails Information saved successfully");
				rm.setResults(booking);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(booking);
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
	@PutMapping("bookings/update/{id}")
	public ResponseEntity<ResponseMessage<AccessoriesDetails>> updateAccessoriesDetails(
			@RequestBody AccessoriesDetailsModel bookingModel, @PathVariable Long id) {
		ResponseMessage<AccessoriesDetails> rm = new ResponseMessage<>();

		try {
			AccessoriesDetails booking = bookingDao.updateAccessoriesDetails(bookingModel, id);
			if (booking != null) {
				rm.setMessage("AccessoriesDetails Information saved successfully");
				rm.setResults(booking);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(booking);
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

	@GetMapping("bookings/{id}")
	public ResponseEntity<ResponseMessage<AccessoriesDetails>> getAccessoriesDetailsDetailsById(@PathVariable Long id) {
		ResponseMessage<AccessoriesDetails> rm = new ResponseMessage<>();

		try {
			AccessoriesDetails booking = bookingDao.getAccessoriesDetailsDetailsById(id);
			if (booking != null) {
				rm.setMessage("AccessoriesDetails deatils are available");
				rm.setResults(booking);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not found");
				rm.setResults(booking);
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

	@GetMapping("bookings")
	public ResponseEntity<ResponseMessage<List<AccessoriesDetails>>> getAccessoriesDetailss() {
		ResponseMessage<List<AccessoriesDetails>> rm = new ResponseMessage<>();

		try {
			List<AccessoriesDetails> bookings = bookingDao.getAllAccessoriesDetailss();
			if (bookings != null) {
				rm.setMessage("AccessoriesDetailss are available");
				rm.setResults(bookings);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("AccessoriesDetailss are not available.");
				rm.setResults(bookings);
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

	/*
	 * @GetMapping("bookings/search/{datefield}") public
	 * ResponseEntity<ResponseMessage<List<AccessoriesDetailsModel>>>
	 * getAccessoriesDetailss(@PathVariable String datefield) {
	 * ResponseMessage<List<AccessoriesDetailsModel>> rm = new ResponseMessage<>();
	 * 
	 * try { List<AccessoriesDetailsModel> bookings =
	 * bookingDao.getDatebasedAccessoriesDetailss(datefield); if (bookings != null)
	 * { rm.setMessage("AccessoriesDetailss are available");
	 * rm.setResults(bookings); rm.setStatusCode(1); } else {
	 * rm.setMessage("AccessoriesDetailss are not available.");
	 * rm.setResults(bookings); rm.setStatusCode(0); } } catch (Exception e) {
	 * 
	 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
	 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
	 * 
	 * throw e; } return new ResponseEntity<>(rm, HttpStatus.OK); }
	 */

	@GetMapping("search/{field}/{value}")
	public ResponseEntity<ResponseMessage<List<AccessoriesDetailsModel>>> searchAccessoriesDetailsDetail(
			@PathVariable String field, @PathVariable String value) {
		ResponseMessage<List<AccessoriesDetailsModel>> rm = new ResponseMessage<>();

		try {
			List<AccessoriesDetailsModel> bookings = bookingDao.searchAccessoriesDetailsInfo(field, value);
			if (bookings != null) {
				rm.setMessage("AccessoriesDetailss are available");
				rm.setResults(bookings);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("AccessoriesDetailss are not available.");
				rm.setResults(bookings);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	/*
	 * @GetMapping("getActiveAccessoriesDetailss/{limit}/{offset}") public
	 * ResponseEntity<ResponseMessage<List<AccessoriesDetailsModel>>>
	 * getActiveAccessoriesDetailss(
	 * 
	 * @PathVariable int limit, @PathVariable int offset) {
	 * ResponseMessage<List<AccessoriesDetailsModel>> rm = new ResponseMessage<>();
	 * 
	 * try { List<AccessoriesDetailsModel> bookings =
	 * bookingDao.getActiveAccessoriesDetailss(limit, offset); if (bookings != null)
	 * { rm.setMessage("AccessoriesDetailss are available");
	 * rm.setResults(bookings); rm.setStatusCode(1); } else {
	 * rm.setMessage("AccessoriesDetailss are not available.");
	 * rm.setResults(bookings); rm.setStatusCode(0); } } catch (Exception e) {
	 * 
	 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
	 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
	 * 
	 * throw e; } return new ResponseEntity<>(rm, HttpStatus.OK); }
	 */
}
