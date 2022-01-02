package com.assetmgmt.util;

import com.assetmgmt.enumeration.LogOperation;
import com.assetmgmt.exception.BadRequestException;
import com.assetmgmt.exception.ForbiddenException;
import com.assetmgmt.exception.RecordNotFoundException;
import com.assetmgmt.exception.UnHandledException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorUtility {

	public static Exception utilityError(Exception e) throws UnHandledException {
		if (e instanceof ForbiddenException)
			throw (ForbiddenException) e;
		else if (e instanceof RecordNotFoundException)
			throw (RecordNotFoundException) e;
		else if (e instanceof BadRequestException)
			throw (BadRequestException) e;
		else
			throw new UnHandledException(e);
	}

	public static Exception utilityError(Exception e, String additioanlInfo) throws UnHandledException {
		if (e instanceof ForbiddenException)
			throw (ForbiddenException) e;
		else if (e instanceof RecordNotFoundException)
			throw (RecordNotFoundException) e;
		else if (e instanceof BadRequestException)
			throw (BadRequestException) e;
		else
			throw new UnHandledException(e, additioanlInfo);
	}

	public static Exception utilityError(Exception e, LogOperation ope) throws UnHandledException {
		if (e instanceof ForbiddenException)
			throw (ForbiddenException) e;
		else if (e instanceof RecordNotFoundException)
			throw (RecordNotFoundException) e;
		else if (e instanceof BadRequestException)
			throw (BadRequestException) e;
		else
			throw new UnHandledException(e, e.getMessage(), ope);
	}

	public static Exception utilityError(Exception e, LogOperation ope, String additionalInfo)
			throws UnHandledException {
		if (e instanceof ForbiddenException)
			throw (ForbiddenException) e;
		else if (e instanceof RecordNotFoundException)
			throw (RecordNotFoundException) e;
		else if (e instanceof BadRequestException)
			throw (BadRequestException) e;
		else
			throw new UnHandledException(e, e.getMessage(), ope, additionalInfo);
	}

}
