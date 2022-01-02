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
public class Accessories extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * @JsonIgnore
	 * 
	 * @Version private int version;
	 */

	@Column(length = 25)
	private String productName;

	@Column(length = 50)
	private String description;

	@Column(length = 30)
	private String status;
	
}
