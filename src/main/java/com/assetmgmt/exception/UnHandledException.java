package com.assetmgmt.exception;

import com.assetmgmt.dto.ErrorLogDto;
import com.assetmgmt.enumeration.LogOperation;
import com.assetmgmt.util.LogWrapper;

public class UnHandledException extends Exception {

	private static final String CLASSNAME = "com.tw";

	public UnHandledException(Exception e) {
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement element : st) {
			if (element.getClassName().contains(CLASSNAME) && !element.getClassName().contains("$")
					&& !element.getMethodName().contains("$")) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder()
						.componentName(element.getClassName() + "." + element.getMethodName() + "()")
						.lineNumber(element.getLineNumber()).exception(e).build());
				break;
			}
		}

	}

	public UnHandledException(Exception e, String additionalInfo) {
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement element : st) {
			if (element.getClassName().contains(CLASSNAME) && !element.getClassName().contains("$")
					&& !element.getMethodName().contains("$")) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder()
						.componentName(element.getClassName() + "." + element.getMethodName() + "()")
						.lineNumber(element.getLineNumber()).exception(e).additionalInfo(additionalInfo).build());
				break;
			}
		}
	}

	public UnHandledException(Exception e, String message, LogOperation operation) {
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement element : st) {
			if (element.getClassName().contains(CLASSNAME) && !element.getClassName().contains("$")
					&& !element.getMethodName().contains("$")) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder()
						.componentName(element.getClassName() + "." + element.getMethodName() + "()")
						.lineNumber(element.getLineNumber()).exception(e).operation(operation).errorMessage(message)
						.build());
				break;
			}
		}
	}

	public UnHandledException(Exception e, String message, LogOperation operation, String additionalInfo) {
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement element : st) {
			if (element.getClassName().contains(CLASSNAME) && !element.getClassName().contains("$")
					&& !element.getMethodName().contains("$")) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder()
						.componentName(element.getClassName() + "." + element.getMethodName() + "()")
						.lineNumber(element.getLineNumber()).exception(e).additionalInfo(additionalInfo)
						.operation(operation).errorMessage(message).build());
				break;
			}
		}

	}

}
