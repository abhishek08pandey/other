package com.onerivet.deskbook.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.ModeOfWorkService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook")
public class ModeOfWorkController {
	
	@Autowired
	private ModeOfWorkService modeOfWorkService;
	
	final static Logger logger = LoggerFactory.getLogger(ModeOfWorkController.class);
	
	public ModeOfWorkController(ModeOfWorkService modeOfWorkService) {
		super();
		this.modeOfWorkService = modeOfWorkService;
	}



	/**
	 * @purpose: Get all mode of works 
	 * @return: List of modeOfWorkDto
	 */
	@GetMapping("/mode-of-works")
	public GenericResponse<List<ModeOfWorkDto>> getModeOfWorks() {
		logger.info("[getModeOfWork()] started");
		GenericResponse<List<ModeOfWorkDto>> genericResponse = new GenericResponse<>(this.modeOfWorkService.getAllModeOfWorks(), null);
		logger.info("[getModeOfWork()] ended");
		return genericResponse;
	}
}
