package com.assetmgmt.controller;

import com.assetmgmt.entity.model.ReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assetmgmt.dao.CommonDAO;
import com.assetmgmt.dto.MessageDto;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.dto.StatisticsDto;
import com.assetmgmt.entity.model.MessageModel;

import java.util.Map;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/common/")
public class CommonController {

	@Autowired
	CommonDAO commonDAO;

	
	@GetMapping("statistics")
	public ResponseEntity<ResponseMessage<StatisticsDto>> getStatistics() {
		ResponseMessage<StatisticsDto> rm = new ResponseMessage<>();

		try {
			StatisticsDto statisticsDto = commonDAO.getStatistics();
			if (statisticsDto != null) {
				rm.setMessage("Statistics are available");
				rm.setResults(statisticsDto);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Statistics are not available.");
				rm.setResults(statisticsDto);
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
	
	@PostMapping("sendmessage")
	public ResponseEntity<ResponseMessage<MessageDto>> sendMessage(@RequestBody MessageModel messageModel) {
		ResponseMessage<MessageDto> rm = new ResponseMessage<>();

		try {
			MessageDto messageDto = commonDAO.sedMessageTo(messageModel);
			if (messageDto != null) {
				rm.setMessage("Messages sent successfully");
				rm.setResults(messageDto);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Messages sent Failed.");
				rm.setResults(messageDto);
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

	//	public ResponseEntity<ResponseMessage<List<MasterLessor>>> getLessors(@PathVariable int limit,@PathVariable int offset) {
	@PostMapping("getreport")
	public ResponseEntity<ResponseMessage<Map<String, Object>>> getReport(@RequestBody ReportModel reportModel) {
		ResponseMessage<Map<String, Object>> rm = new ResponseMessage<>();

		try {
			Map<String, Object> reportDto = commonDAO.getReportDetails(reportModel);
			if (reportDto != null) {
				rm.setMessage("Messages sent successfully");
				rm.setResults(reportDto);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Messages sent Failed.");
				rm.setResults(reportDto);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
}
