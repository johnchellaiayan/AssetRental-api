package com.assetmgmt.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.assetmgmt.entity.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.assetmgmt.entity.Role;
import com.assetmgmt.entity.User;

import lombok.Data;

@Entity
@Data
public class MasterAssets extends UserDateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	/*@JsonIgnore
	@Version
	private int version;*/

	@Column(length = 30)
	private String BuildingName;
	
	@Column(length = 30)
	private String blockNo;
	
	@Column(length = 30)
	private String floorNo;
	
	@Column(length = 30)
	private String doorNo;

	@Column(columnDefinition = "TEXT")
	private String address;

	@Column(length = 30)
	private String city;

	@Column(length = 30)
	private String pincode;

	@Column(length = 30)
	private String totalsqft;

	@Column(length = 30)
	private String status;

	/*
	 * @JsonIgnore
	 * 
	 * @ManyToMany(fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "rental_accesories", joinColumns = { @JoinColumn(name =
	 * "user_id") }, inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "role_id") }) private Set<MasterAccessoriesDetails>
	 * acessoriesdetails;
	 */
	
}
