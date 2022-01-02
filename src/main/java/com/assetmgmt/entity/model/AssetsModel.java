package com.assetmgmt.entity.model;

import lombok.Data;

@Data
public class AssetsModel {

	private String id;
	private String buildingName;
	private String blockNo;
	private String floorNo;
	private String doorNo;
	private String address;
	private String city;
	private String pincode;
	private String totalsqft;
	private String status;

}
