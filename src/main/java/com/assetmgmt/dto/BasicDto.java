package com.assetmgmt.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class BasicDto {
	private Long id;
	private String name;

	public BasicDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
