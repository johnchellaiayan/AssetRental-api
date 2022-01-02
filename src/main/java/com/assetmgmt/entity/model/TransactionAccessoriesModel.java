package com.assetmgmt.entity.model;

import javax.persistence.Column;

import lombok.Data;

@Data
public class TransactionAccessoriesModel {

	private String id;
	private String agreementId;
	private String productId;
	private String startDate;
	private String endDate;
	private String receivedDate;
	private String baseAmount;
	private String cgst;
	private String sgst;
	private String totalAmount;
}
