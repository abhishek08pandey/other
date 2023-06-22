package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onerivet.deskbook.models.payload.ModeOfWorkDto;
import com.onerivet.deskbook.repository.ModeOfWorkRepo;
import com.onerivet.deskbook.services.ModeOfWorkService;


@Transactional
@Service
public class ModeOfWorkServiceImpl implements ModeOfWorkService {
	
	
	@Autowired
	private ModeOfWorkRepo modeOfWorkRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	final static Logger logger = LoggerFactory.getLogger(ModeOfWorkServiceImpl.class);
	
	public ModeOfWorkServiceImpl(ModeOfWorkRepo modeOfWorkRepo, ModelMapper modelMapper) {
		super();
		this.modeOfWorkRepo = modeOfWorkRepo;
		this.modelMapper = modelMapper;
	}



	@Override
	public List<ModeOfWorkDto> getAllModeOfWorks() {
		logger.info("[getAllModeOfWorks()] started");
		logger.info("[getAllModeOfWorks()] ended");

		return this.modeOfWorkRepo.findAll().stream()
				.map((modeOfWork) -> this.modelMapper.map(modeOfWork, ModeOfWorkDto.class))
				.collect(Collectors.toList());
	}
}
