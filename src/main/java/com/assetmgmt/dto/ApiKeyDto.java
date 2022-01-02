package com.assetmgmt.dto;

import lombok.Data;

@Data
public class ApiKeyDto {
	private String domain;
	private String type;
	private String fromDate;
	private String customerName;
}
