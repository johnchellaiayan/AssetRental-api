package com.assetmgmt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.assetmgmt.entity.audit.UserDateAudit;

import lombok.Data;

@Entity
@Data
public class RentalAgreements extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long agreementId; 

	/*
	 * @JsonIgnore
	 * 
	 * @Version private int version;
	 */
	
	@Column(length = 30)
	private String lessorId;
	
	@Column(length = 30)
	private String lesseeId;
	
	@Column(length = 30)
	private String assetDetailsId;
	
	@Column(length = 150)
	private String agreementDate;
	
	@Column(length = 150)
	private String description;
	
	@Column(length = 150)
	private String saccode;

	@Column(length = 150)
	private String startRentalPeriod;
	
	@Column(length = 150)
	private String endRentalPeriod;

	@Column(length = 100)
	private String rentAmount;

}
