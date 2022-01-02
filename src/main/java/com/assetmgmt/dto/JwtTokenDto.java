package com.assetmgmt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class JwtTokenDto {
	@JsonProperty(value = "access_token")
	private String accessToken;
	
	@JsonProperty(value = "refresh_token")
	private String refreshToken;
	
	@JsonProperty(value = "expires_in")
	private String expiresIn;
}
