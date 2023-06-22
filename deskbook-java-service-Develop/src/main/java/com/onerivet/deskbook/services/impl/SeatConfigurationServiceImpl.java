package com.onerivet.deskbook.services.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onerivet.deskbook.exception.ResourceNotFoundException;
import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.FloorDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.repository.ColumnDetailsRepo;
import com.onerivet.deskbook.repository.FloorRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.services.SeatConfigurationService;


@Transactional
@Service
public class SeatConfigurationServiceImpl implements SeatConfigurationService {
	
	@Autowired
	private FloorRepo floorRepo;
	
	@Autowired
	private ColumnDetailsRepo columnDetailsRepo;
	
	@Autowired
	private SeatNumberRepo seatNumberRepo;
	
	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	final static Logger logger = LoggerFactory.getLogger(SeatConfigurationServiceImpl.class);
	
	

	public SeatConfigurationServiceImpl(FloorRepo floorRepo, ColumnDetailsRepo columnDetailsRepo,
			SeatNumberRepo seatNumberRepo, SeatConfigurationRepo seatConfigurationRepo, ModelMapper modelMapper) {
		super();
		this.floorRepo = floorRepo;
		this.columnDetailsRepo = columnDetailsRepo;
		this.seatNumberRepo = seatNumberRepo;
		this.seatConfigurationRepo = seatConfigurationRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<FloorDto> getAllFloors(int cityId) {
		logger.info("[getAllFloors()] started");
		
		List<Floor> floors = this.floorRepo.findByCity(new City(cityId));
		if(floors.isEmpty()) {
			logger.error("City does not exist with id "+cityId+" !");
			throw new ResourceNotFoundException("City does not exist!");
		}
		
		logger.info("[getAllFloors()] ended");
		return floors.stream().map((floor) -> this.modelMapper.map(floor, FloorDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ColumnDetailsDto> getAllColumns(int floorId) {
		logger.info("[getAllColumns()] started");

		List<ColumnDetails> columns = this.columnDetailsRepo.findByFloor(new Floor(floorId));
		if(columns.isEmpty()) {
			logger.error("Floor does not exist with id "+floorId+" !");
			throw new ResourceNotFoundException("Floor does not exist!");
		}
		logger.info("[getAllColumns()] ended");

		return columns.stream()
				.map((column) -> this.modelMapper.map(column, ColumnDetailsDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<SeatNumberDto> getAllSeats(int columnId) {
		logger.info("[getAllSeats()] started");

		List<SeatNumber> seats = this.seatNumberRepo.findByColumn(new ColumnDetails(columnId));
		if(seats.isEmpty()) {
			logger.error("Column does not exist with id "+columnId+" !");
			throw new ResourceNotFoundException("Column does not exist!");
		}
		
		List<SeatNumber> bookedSeats = this.seatConfigurationRepo.findSeats(seats);
		Collections.sort(bookedSeats, new Comparator<SeatNumber>() {

			@Override
			public int compare(SeatNumber seat1, SeatNumber seat2) {

				 return Integer.compare(seat1.getId(), seat2.getId());
			}
		
		});
		
		for (int i = 0, j = 0; j < bookedSeats.size() && i<seats.size();) {
			if (seats.get(i).getId() == bookedSeats.get(j).getId()) {
				seats.get(i).setBooked(true);
				j++;
				
			}
			i++;
		}
		logger.info("[getAllSeats()] ended");

		return seats.stream()
				.map((seatNumber) -> this.modelMapper.map(seatNumber, SeatNumberDto.class))
				.collect(Collectors.toList());
	}


}
