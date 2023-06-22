package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onerivet.deskbook.models.payload.DesignationDto;
import com.onerivet.deskbook.repository.DesignationRepo;
import com.onerivet.deskbook.services.DesignationService;


@Transactional
@Service
public class DesignationServiceImpl implements DesignationService {
	
	@Autowired
	private DesignationRepo designationRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	final static Logger logger = LoggerFactory.getLogger(DesignationServiceImpl.class);
	
	public DesignationServiceImpl(DesignationRepo designationRepo, ModelMapper modelMapper) {
		super();
		this.designationRepo = designationRepo;
		this.modelMapper = modelMapper;
	}


	@Override
	public List<DesignationDto> getAllDesignations() {
		logger.info("[getAllDesignations()] started");
		logger.info("[getAllDesignations()] ended");
		return this.designationRepo.findAll().stream()
				.map((designation) -> this.modelMapper.map(designation, DesignationDto.class))
				.collect(Collectors.toList());
	}

}
