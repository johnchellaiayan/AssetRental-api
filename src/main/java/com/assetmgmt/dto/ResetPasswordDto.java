package com.assetmgmt.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ResetPasswordDto {
	@NotNull(message = "username is required")
	private String username;
	
	@NotNull(message = "new password is required")
	private String newPassword;
	
	@NotNull(message = "otp is required")
	private String code;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
	

}
