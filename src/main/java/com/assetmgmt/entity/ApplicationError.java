package com.assetmgmt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.assetmgmt.enumeration.LogOperation;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationError {

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userId;

	private Long loginId;

	private String tagId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date errorTimestamp;

	@Enumerated(EnumType.STRING)
	private LogOperation operation;

	private String applicationScreen;

	private String application;

	private String host;

	@JsonIgnore
	private String componentName;

	private String errorType;

	private String errorSeverity;

	private String errorCode;

	private Integer lineNum;

	private String errorMessage;

	@Column(columnDefinition = "TEXT")
	private String stacktrace;

	@JsonIgnore
	private Integer createdBy;

	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	private String additionalInfo;

	private String url;

	private String inputJson;

	@JsonIgnore
	private String httpMethod;

	@JsonIgnore
	private Integer modifiedBy;

	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifiedAt;

}
