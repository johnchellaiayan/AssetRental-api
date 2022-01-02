package com.assetmgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.assetmgmt.entity.audit.UserDateAudit;
import com.assetmgmt.service.StringPrefixedSequenceIdGenerator;

import lombok.Data;

@Entity
@Data
public class MasterLessee extends UserDateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * @JsonIgnore
	 * 
	 * @Version private int version;
	 */

	@Column(length = 100)
	private String name; 

	@Column(columnDefinition = "TEXT")
	private String address;

	@Column(length = 30)
	private String city;

	@Column(length = 30)
	private String pincode;

	@Column(length = 30)
	private String mobileNo1;

	@Column(length = 30)
	private String mobileNo2;
	
	@Column(length = 30)
	private String phoneNo1;
	
	@Column(length = 30)
	private String panNumber;

	@Column(length = 30)
	private String gstTin;

	@Column(columnDefinition = "TEXT")
	private String remarks;

}
