package com.assetmgmt.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class BasicModel {
	private Long id;
	private String name;

	public BasicModel(Number id, String name) {
		super();
		this.id = id.longValue();
		this.name = name;
	}

	public BasicModel(Long id, String name) {
		super();
		this.id = id;
		this.name = name;  
	}
}
