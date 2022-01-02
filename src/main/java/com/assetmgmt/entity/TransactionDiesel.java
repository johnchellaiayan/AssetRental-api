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
public class TransactionDiesel extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dieselId;

	@Column(length = 30)
	private String agreementId;
	
	@Column(length = 150)
	private String description;

	@Column(length = 150)
	private String startDate;
	
	@Column(length = 150)
	private String endDate;

	@Column(length = 100)
	private String baseAmount;
	
	@Column(length = 150)
	private String cgst;

	@Column(length = 100)
	private String sgst;

}
