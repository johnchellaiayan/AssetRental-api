package com.assetmgmt.dto;

import java.util.Map;

import com.assetmgmt.entity.model.UserModel;

import lombok.Data;

@Data
public class TokenDto {
	private String accessToken;
	private String refreshToken;
	private String expiresIn;	
	private UserModel user;
	private Map<String,Object> permissions;
}
