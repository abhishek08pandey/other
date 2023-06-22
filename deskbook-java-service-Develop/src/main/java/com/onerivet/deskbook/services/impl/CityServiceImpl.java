package com.onerivet.deskbook.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onerivet.deskbook.models.payload.CityDto;
import com.onerivet.deskbook.repository.CityRepo;
import com.onerivet.deskbook.services.CityService;

@Transactional
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private ModelMapper modelMapper;

	final static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	public CityServiceImpl(CityRepo cityRepo, ModelMapper modelMapper) {
		super();
		this.cityRepo = cityRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<CityDto> getAllCities() {
		logger.info("[getAllCities()] started");
		logger.info("[getAllCities()] ended");

		return this.cityRepo.findAll().stream().map((city) -> this.modelMapper.map(city, CityDto.class))
				.collect(Collectors.toList());
	}
}
