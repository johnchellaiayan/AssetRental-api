package com.assetmgmt.entity.model;

import lombok.Data;

@Data
public class ReportModel {

	private Long id;
	private String lessorid;
	private String lesseeid;
	private String startdate;
	private String enddate;
	private String reporttype;
}
