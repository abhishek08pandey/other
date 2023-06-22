package com.onerivet.deskbook.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.SeatConfigurationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook")
public class SeatConfigurationController {
	
	@Autowired
	private SeatConfigurationService seatConfigurationService;
	
	final static Logger logger = LoggerFactory.getLogger(SeatConfigurationController.class);
	
	public SeatConfigurationController(SeatConfigurationService seatConfigurationService) {
		super();
		this.seatConfigurationService = seatConfigurationService;
	}


	/**
	 * @purpose: Get all floor  
	 * @return: List of floorDto
	 */
	@GetMapping("/floors/{cityId}")
	public GenericResponse<List<FloorDto>> getFloors(@PathVariable("cityId")  @Positive(message = "CityId must be positive") int cityId) {
		
		logger.info("[getFloors()] started");
		
		GenericResponse<List<FloorDto>> genericResponse = new GenericResponse<>(this.seatConfigurationService.getAllFloors(cityId), null);
		
		logger.info("[getFloors()] ended");
		return genericResponse;
	}
	
	
	/**
	 * @purpose: Get all columns 
	 * @return: List of columnDetailsDto
	 */
	@GetMapping("/columns/{floorId}")
	public GenericResponse<List<ColumnDetailsDto>> getColumns(@PathVariable("floorId") @Positive(message = "FloorId must be positive") int floorId) {
		logger.info("[getColumns()] started");
		GenericResponse<List<ColumnDetailsDto>> genericResponse = new GenericResponse<>(this.seatConfigurationService.getAllColumns(floorId), null);
		logger.info("[getColumns()] ended");
		return genericResponse;
	}
	
	
	/**
	 * @purpose: Get all seats 
	 * @return: List of seatDto
	 */
	@GetMapping("/seats/{columnId}")
	public GenericResponse<List<SeatNumberDto>> getSeats(@PathVariable("columnId") @Positive(message = "ColumnId must be positive") int columnId) {
		logger.info("[getSeats()] started");
		GenericResponse<List<SeatNumberDto>> genericResponse = new GenericResponse<>(this.seatConfigurationService.getAllSeats(columnId), null);
		logger.info("[getSeats()] ended");
		return genericResponse;
	}
}
