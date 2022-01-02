package com.assetmgmt.dto;

import org.springframework.data.domain.Sort.Direction;

import lombok.Data;

@Data
public class PaginationDto {
	private Integer pageStart;
	private Integer pageSize;
	private String orderBy;
	private Direction direction;
}
