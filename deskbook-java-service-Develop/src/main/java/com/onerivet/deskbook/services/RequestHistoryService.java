package com.onerivet.deskbook.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.onerivet.deskbook.models.payload.RequestHistoryDto;

public interface RequestHistoryService {

	public List<RequestHistoryDto> getRequestHistory(String employeeId, Pageable pageble, Integer requestStatus);

	public List<RequestHistoryDto> searchByFirstNameOrLastName(String employeeId,String name, Pageable pageble);

//	public RequestHistoryTakeActionDto takeAction(RequestHistoryDto dto);

}