package com.assetmgmt.entity.model;

import javax.persistence.Column;

import lombok.Data;

@Data
public class AccessoriesAmcModel {

	private String id;
	private String lessorId;
	private String lesseeId;
	private String agreementId;
	private String productId;
	private String modelName;
	private String description;
	private String startDate;
	private String endDate;
	private String baseAmount;
	private String cgst;
	private String sgst;
	private String totalAmount;
}
