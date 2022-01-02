package com.assetmgmt.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class BasicLookupDto {
	private String code;
	private String value;
	public BasicLookupDto(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}
}
