package com.assetmgmt.entity;

import javax.persistence.*;

import com.assetmgmt.entity.audit.UserDateAudit;

import lombok.Data;

@Entity
@Data
public class MasterLessor extends UserDateAudit {

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

	@ManyToOne
	@JoinColumn(name = "bank_details_id")
	private BankDetails bankDetails;

}
