package com.onerivet.deskbook.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.WorkingDayService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook")
public class WorkingDayController {
	
	@Autowired
	private WorkingDayService workingDayService;
	
	final static Logger logger = LoggerFactory.getLogger(WorkingDayController.class);
	
	public WorkingDayController(WorkingDayService workingDayService) {
		super();
		this.workingDayService = workingDayService;
	}


	/**
	 * @purpose: Get all working days 
	 * @return: List of workingDayDto
	 */
	@GetMapping("/working-days")
	public GenericResponse<List<WorkingDayDto>> getWorkingDays() {
		logger.info("[getWorkingDays()] started");
		GenericResponse<List<WorkingDayDto>> genericResponse = new GenericResponse<>(this.workingDayService.getAllWorkingDays(), null);
		logger.info("[getWorkingDays()] ended");
		return genericResponse;
	}
}
