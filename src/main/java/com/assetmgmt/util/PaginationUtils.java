package com.assetmgmt.util;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assetmgmt.dto.ResponseMessage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
public class PaginationUtils<T> {
	public ResponseMessage<List<T>> getPagingResults(Page<T> page,String entityName) {
		ResponseMessage<List<T>> message = new ResponseMessage<>();
		Pageable pageInfo = page.getPageable();
		message.setPageSize(pageInfo.getPageSize());
		message.setPageStart(pageInfo.getOffset());
		message.setCurrentPage(pageInfo.getPageNumber());
		message.setTotalPage(page.getTotalPages());
		message.setTotalRecords(page.getTotalElements());		
		List<T> list=page.getContent();
		message.setStatusCode((list!=null && !list.isEmpty())?1:0);
		message.setResults(page.getContent());
		message.setMessage((list!=null && !list.isEmpty())?entityName+" lists are available":entityName+" lists are empty");
		return message;
	}

}
