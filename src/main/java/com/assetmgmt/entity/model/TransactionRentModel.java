package com.assetmgmt.entity.model;

import javax.persistence.Column;

import lombok.Data;

@Data
public class TransactionRentModel {

	private String id;
	private String agreementId;
	private String description;
	private String startDate;
	private String endDate;
	private String rentAmount;
	private String balanceAmount;
}
