package com.assetmgmt.entity.model;

import javax.persistence.Column;

import lombok.Data;

@Data
public class AccessoriesModel {

	private String id;
	private String productName;
	private String description;
	private String status;
	
}
