package com.assetmgmt.entity.model;

import lombok.Data;

@Data
public class TransactionDieselModel {

	private String id;
	private String agreementId;
	private String description;
	private String startDate;
	private String endDate;
	private String baseAmount;
	private String cgst;
	private String sgst;
}
