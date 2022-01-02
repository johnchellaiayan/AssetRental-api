package com.assetmgmt.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.assetmgmt.exception.ForbiddenException;

public class BaseJsonDto {
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			throw new ForbiddenException(e.getLocalizedMessage());
		}
	}
}
