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
public class AccessoriesDetails extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * @JsonIgnore
	 * 
	 * @Version private int version;
	 */

	@Column(length = 30)
	private String productName;

	@Column(length = 30)
	private String modelName;

	@Column(length = 150)
	private String productSlNo;

	@Column(length = 150)
	private String description;

	@Column(length = 150)
	private String purchaseDate;

	@Column(length = 150)
	private String warrantyYears;

	@Column(length = 100)
	private String purchaseAmount;
}
