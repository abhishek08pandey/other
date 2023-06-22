package com.onerivet.deskbook.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.CityService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook")
public class CityController {

	@Autowired
	private CityService cityService;
	
	final static Logger logger = LoggerFactory.getLogger(CityController.class);

	public CityController(CityService cityService) {
		super();
		this.cityService = cityService;
	}



	/**
	 * @purpose: Get all cities
	 * @return: List of cityDto
	 */
	@GetMapping("/cities")
	public GenericResponse<List<CityDto>> getCities() {
		
		logger.info("[getCities()] started");
		GenericResponse<List<CityDto>> genericResponse = new GenericResponse<>(this.cityService.getAllCities(), null);
		logger.info("[getCities()] ended");
		return genericResponse;
	}

}
