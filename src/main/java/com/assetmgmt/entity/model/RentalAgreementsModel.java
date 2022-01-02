package com.assetmgmt.entity.model;

import java.util.Date;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RentalAgreementsModel {
	@ApiModelProperty(hidden = true)
	private Long id;
	private String lessorId;
	private String lesseeId;
	private String assetDetailsId;
	private String agreementDate;
	private String description;
	private String saccode;
	private String startRentalPeriod;
	private String endRentalPeriod;
	private String rentAmount;
}