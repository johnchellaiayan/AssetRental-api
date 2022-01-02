package com.assetmgmt.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.assetmgmt.dto.ErrorLogDto;
import com.assetmgmt.util.LogWrapper;

@Repository
@Transactional
public class AuthDao {

	

	public String forgetPassword(String username) {
		username = username.replaceFirst("^0+(?!$)", "");
		String phoneNumber = null;
		Integer loginId = null;

		String message = "OTP for Covid is Confidential.Please Do Not Share it with anyone.Your OTP is ";
		try {
		
		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().errorMessage("Sms not send").exception(e).build());
		}

		if (phoneNumber != null) {
			Integer index = 8;
			if (phoneNumber.contains("91") || phoneNumber.contains("+91")) {
				index = 10;
			}
			phoneNumber = "XXXXXXX" + phoneNumber.substring(index, phoneNumber.length());
		}

		return "Your otp is sent to your registered mobile number." + phoneNumber;
	}

	public String resetPassword(String username, String otp, String password) {
		username = username.replaceFirst("^0+(?!$)", "");
		return "Password reset successfully";
	}

}
