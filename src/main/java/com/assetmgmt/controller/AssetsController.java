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

import com.assetmgmt.dao.AssetsDao;
import com.assetmgmt.dto.ResponseMessage;
import com.assetmgmt.entity.MasterAssets;
import com.assetmgmt.entity.model.AssetsModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/assets/")
public class AssetsController {

	@Autowired
	private AssetsDao assetsDao;

	@PostMapping("assets")
	public ResponseEntity<ResponseMessage<MasterAssets>> saveUser(@RequestBody AssetsModel assetsModel) {
		ResponseMessage<MasterAssets> rm = new ResponseMessage<>();

		try {
			MasterAssets assets = assetsDao.saveAssets(assetsModel);
			if (assets != null) {
				rm.setMessage("Assets Information saved successfully");
				rm.setResults(assets);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Assets Record not saved");
				rm.setResults(assets);
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
	@PutMapping("assetss/{id}")
	public ResponseEntity<ResponseMessage<MasterAssets>> updateUser(@RequestBody AssetsModel assetsModel,
			@PathVariable Long id) {
		ResponseMessage<MasterAssets> rm = new ResponseMessage<>();

		try {
			System.out.println("AssetsId=" + id);
			MasterAssets assets = assetsDao.updateAssets(assetsModel, id);
			if (assets != null) {
				rm.setMessage("Assets Information saved successfully");
				rm.setResults(assets);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(assets);
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

	@GetMapping("assetss/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<MasterAssets>>> getAssetss(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<MasterAssets>> rm = new ResponseMessage<>();

		try {
			List<MasterAssets> assetss = assetsDao.getAllAssets(limit,offset);
			if (assetss != null) {
				rm.setMessage("Assetss are available");
				rm.setResults(assetss);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Assetss are not available.");
				rm.setResults(assetss);
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

	@GetMapping("assetss/{id}")
	public ResponseEntity<ResponseMessage<MasterAssets>> getAssetsDetail(@PathVariable Long id) {
		ResponseMessage<MasterAssets> rm = new ResponseMessage<>();

		try {
			MasterAssets assetss = assetsDao.getAssetsDetail(id).get(0);
			if (assetss != null) {
				rm.setMessage("Assets details are available");
				rm.setResults(assetss);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Assets details are not available.");
				rm.setResults(assetss);
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
	public ResponseEntity<ResponseMessage<List<MasterAssets>>> searchAssetsDetail(
			@PathVariable String value) {
		ResponseMessage<List<MasterAssets>> rm = new ResponseMessage<>();

		try {
			List<MasterAssets> assetss = assetsDao.searchAssetsInfo(value);
			if (assetss != null) {
				rm.setMessage("Assets's are available");
				rm.setResults(assetss);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Assets's are not available.");
				rm.setResults(assetss);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
