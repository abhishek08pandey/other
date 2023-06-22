package com.onerivet.deskbook.services;

import java.time.LocalDate;
import java.util.List;

import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.payload.SeatViewDto;

public interface SeatViewService {
	
	public SeatInformationViewDto seatInformationById(LocalDate bookingDate, int seatid) throws Exception;

	public List<SeatViewDto> getSeatView(LocalDate bookingDate, Integer cityId, Integer floorId);

}
