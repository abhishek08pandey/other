package com.onerivet.deskbook.services;

import com.onerivet.deskbook.models.payload.AcceptRejectDto;
import com.onerivet.deskbook.models.payload.SeatRequestInformationDto;

import jakarta.mail.MessagingException;

public interface SeatRequestService {

	public String requestSeat(String empId, SeatRequestInformationDto seatRequestInformationDto) throws MessagingException;
	
	public String acceptReject(String employeeId, AcceptRejectDto takeAction) throws MessagingException;
	
}