package com.assetmgmt.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.assetmgmt.entity.audit.UserDateAudit;

import lombok.Data;

@Entity
@Data
public class RentalAgreements extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long agreementId;

	@JsonIgnore
	@JoinColumn(name = "lessor_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private MasterLessor lessor;

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
