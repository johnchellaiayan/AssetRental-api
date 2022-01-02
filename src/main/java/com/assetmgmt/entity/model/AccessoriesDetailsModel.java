package com.assetmgmt.entity.model;

import javax.persistence.Column;

import lombok.Data;

@Data
public class AccessoriesDetailsModel {

	private Long id;
	private String productName;
	private String modelName;
	private String productSlNo;
	private String description;
	private String purchaseDate;
	private String warrantyYears;
	private String purchaseAmount;
}
