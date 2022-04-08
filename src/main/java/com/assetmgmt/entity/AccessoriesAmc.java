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
public class AccessoriesAmc extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long amcId;

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
	private String agreementId;
	
	@Column(length = 30)
	private String productId;
	
	@Column(length = 30)
	private String modelName;
	
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
	
	@Column(length = 100)
	private String totalAmount;

}
