package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onerivet.deskbook.models.payload.WorkingDayDto;
import com.onerivet.deskbook.repository.WorkingDaysRepo;
import com.onerivet.deskbook.services.WorkingDayService;


@Transactional
@Service
public class WorkingDayServiceImpl implements WorkingDayService {
	
	@Autowired
	private WorkingDaysRepo workingDaysRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	final static Logger logger = LoggerFactory.getLogger(WorkingDayServiceImpl.class);

	public WorkingDayServiceImpl(WorkingDaysRepo workingDaysRepo, ModelMapper modelMapper) {
		super();
		this.workingDaysRepo = workingDaysRepo;
		this.modelMapper = modelMapper;
	}



	@Override
	public List<WorkingDayDto> getAllWorkingDays() {
		logger.info("[getAllWorkingDays()] started");
		logger.info("[getAllWorkingDays()] ended");

		return this.workingDaysRepo.findAll().stream().map((day) -> this.modelMapper.map(day, WorkingDayDto.class))
				.collect(Collectors.toList());
	}

}
