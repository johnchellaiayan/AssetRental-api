package com.assetmgmt.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.assetmgmt.dto.AuditLogDto;
import com.assetmgmt.dto.ErrorLogDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class LogWrapper {

	
	private static final Logger logger = LogManager.getLogger(LogWrapper.class);

	private static HttpServletRequest getCurrentHttpRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			return ((ServletRequestAttributes) requestAttributes).getRequest();
			
		}
		return null;
	}

	@Async
	public static void logInfoDetails(AuditLogDto auditLogDto) {

		HttpServletRequest request = getCurrentHttpRequest();
		String requestUrl = "";
		String httpMethod = "";

		if (request != null) {
			requestUrl = request.getRequestURI();
			httpMethod = request.getMethod();
		}

		String host = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getRemoteAddr();

		StackTraceElement[] st = new Throwable().getStackTrace();
		String className = st[1].getClassName() + "." + st[1].getMethodName() + "()";

		ThreadContext.put("UserID", "1");
		ThreadContext.put("LoginId", "1");
		ThreadContext.put("RoleId", String.valueOf(auditLogDto.getRoleId()));
		ThreadContext.put("TagId", auditLogDto.getTagId());
		ThreadContext.put("msg", auditLogDto.getMessage());
		ThreadContext.put("className", className);
		ThreadContext.put("Ope", String.valueOf(auditLogDto.getOperation()));
		ThreadContext.put("applicationScreen ", auditLogDto.getApplicationScreen());
		ThreadContext.put("TableName", auditLogDto.getTableName());
		ThreadContext.put("CreatedBy", "");
		ThreadContext.put("Application", "API");
		ThreadContext.put("host", host);
		ThreadContext.put("AdditionalInfo", auditLogDto.getAdditionalInfo());
		ThreadContext.put("RequestUrl", requestUrl);
		ThreadContext.put("InputJson", auditLogDto.getInputJson());
		ThreadContext.put("HttpMethod", httpMethod);

		if (!auditLogDto.getMessage().isEmpty() && !className.isEmpty()) {
			logger.info("");
		}
		ThreadContext.clearMap();
	}

	@Async
	public static void logErrorDetails(ErrorLogDto errorLogDto) {
		
		String host = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getRemoteAddr();

		HttpServletRequest request = getCurrentHttpRequest();

		String requestUrl = "";
		if (request != null) {
			requestUrl = request.getRequestURI();
		}

		String componentName = errorLogDto.getComponentName() != null ? errorLogDto.getComponentName() : null;
		if (componentName == null) {
			StackTraceElement[] st = new Throwable().getStackTrace();
			componentName = st[1].getClassName() + "." + st[1].getMethodName() + "()";

		}

		ThreadContext.put("UserID", "56");
		ThreadContext.put("LoginId", "80");
		ThreadContext.put("TagId", errorLogDto.getTagId());
		ThreadContext.put("className", componentName);
		ThreadContext.put("lineNumber", String.valueOf(errorLogDto.getLineNumber()));
		ThreadContext.put("Operation", String.valueOf(errorLogDto.getOperation()));
		ThreadContext.put("applicationScreen ", errorLogDto.getApplicationScreen());
		ThreadContext.put("CreatedBy", "");
		ThreadContext.put("ErrType", errorLogDto.getErrorType());
		ThreadContext.put("ErrCode", errorLogDto.getErrorCode());
		ThreadContext.put("Application", "API");
		ThreadContext.put("AdditionalInfo", errorLogDto.getAdditionalInfo());
		ThreadContext.put("RequestUrl", requestUrl);
		ThreadContext.put("host", host);


		logger.error(errorLogDto.getErrorMessage() != null ? errorLogDto.getErrorMessage()
				: errorLogDto.getException().getMessage(), errorLogDto.getException());
		ThreadContext.clearMap();
	}

}
