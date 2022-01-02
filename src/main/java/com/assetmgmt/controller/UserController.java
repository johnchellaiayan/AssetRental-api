package com.assetmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assetmgmt.dao.UserDao;
import com.assetmgmt.dto.ErrorLogDto;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.User;
import com.assetmgmt.entity.model.UserModel;
import com.assetmgmt.enumeration.LogOperation;
import com.assetmgmt.util.LogWrapper;
import com.assetmgmt.util.PaginationUtils;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserDao userDao;

	@SuppressWarnings({ "unused", "rawtypes" })
	@Autowired
	private PaginationUtils pageUtil;

	@PostMapping(value = { "new-user-registeration" })
	@ApiOperation(value = "register new user")
	public ResponseEntity<ResponseMessage<User>> saveUser(@RequestBody UserModel userDto) {
		ResponseMessage<User> rm = new ResponseMessage<>();

		try {
			User user = userDao.saveUser(userDto);
			if (user != null) {
				rm.setMessage("User details save successfully");
				rm.setResults(user);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(user);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.DELETE).errorMessage(e.getMessage())
					.exception(e).build());
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	@GetMapping("users")
	public ResponseEntity<ResponseMessage<List<User>>> getUsers() {
		ResponseMessage<List<User>> rm = new ResponseMessage<>();

		try {
			List<User> users = userDao.getUsers();
			if (users != null) {
				rm.setMessage("Users are available");
				rm.setResults(users);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Users are not available.");
				rm.setResults(users);
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
