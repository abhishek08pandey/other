package com.onerivet.deskbook.controllers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.DesignationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook")
public class DesignationController {
	
	@Autowired
	private DesignationService designationService;
	
	final static Logger logger = LoggerFactory.getLogger(DesignationController.class);
	
	public DesignationController(DesignationService designationService) {
		super();
		this.designationService = designationService;
	}


	/**
	 * @purpose: Get all designations 
	 * @return: List of designationDto
	 */
	@GetMapping("/designations")
	public GenericResponse<List<DesignationDto>> getDesignations() {
		logger.info("[getDesignations()] started");
		GenericResponse<List<DesignationDto>> genericResponse = new GenericResponse<>(this.designationService.getAllDesignations(), null);
		logger.info("[getDesignations()] ended");
		return genericResponse;
	}
}
