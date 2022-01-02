package com.assetmgmt.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.assetmgmt.enumeration.LogOperation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuditLogDto {

	private Long userId;
	private Long loginId;
	private Integer roleId;
	private String tagId;
	private LogOperation operation;
	private String applicationScreen;
	private String message;
	private String componentName;
	private String tableName;
	private String stackTrace;
	private Long createdBy;
	private Date createdAt;
	private String additionalInfo; 
	private String inputJson; 
	
	@JsonIgnore
	private String httpMethod; 
	
	@JsonIgnore
	private String requestUrl;

}
