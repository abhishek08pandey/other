package com.onerivet.deskbook.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.payload.SeatViewDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.SeatViewService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook/seat-view")
public class SeatViewController {
	
	@Autowired
	private SeatViewService seatViewService;
	
	final static Logger logger = LoggerFactory.getLogger(SeatViewController.class);
	
	public SeatViewController(SeatViewService seatViewService) {
		super();
		this.seatViewService = seatViewService;
	}

	
	/**
	 * @purpose: Get seat details
	 * @param: date, seatId
	 * @return: Employee details of particular seat
	 */
	@GetMapping("/get-seat-details")
    public GenericResponse<SeatInformationViewDto> seatInformation(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate date, @RequestParam("seatId") int seatid) throws Exception {
        logger.info("[seatInformation()] started");
		GenericResponse<SeatInformationViewDto> genericResponse = new GenericResponse<>(this.seatViewService.seatInformationById(date, seatid), null);
		logger.info("[seatInformation()] ended");
        return genericResponse;
    }	

	/**
	 * @purpose: Get all seat on floor
	 * @param: date, cityId, seatId
	 * @return: List of seats on floor
	 */
	@GetMapping("")
	public GenericResponse<List<SeatViewDto>> getView(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate date, @RequestParam("cityId") int cityId, @RequestParam("floorId") int floorId) {
		logger.info("[getView()] started");
		GenericResponse<List<SeatViewDto>> response = new GenericResponse<>(this.seatViewService.getSeatView(date, cityId, floorId), null);
		logger.info("[getView()] ended");
		return response;
	}

}
