package com.onerivet.deskbook.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;
import com.onerivet.deskbook.models.entity.SeatView;
import com.onerivet.deskbook.models.entity.WorkingDay;
import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.payload.SeatViewDto;
import com.onerivet.deskbook.models.payload.TemporarySeatOwnerDto;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.repository.SeatRequestRepo;
import com.onerivet.deskbook.services.SeatViewService;


@Transactional
@Service
public class SeatViewServiceImpl implements SeatViewService {

	@Autowired
	private SeatNumberRepo seatNumberRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SeatRequestRepo seatRequestRepo;

	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;

	final static Logger logger = LoggerFactory.getLogger(SeatViewServiceImpl.class);
	
	public SeatViewServiceImpl(SeatNumberRepo seatNumberRepo, ModelMapper modelMapper, SeatRequestRepo seatRequestRepo,
			SeatConfigurationRepo seatConfigurationRepo) {
		super();
		this.seatNumberRepo = seatNumberRepo;
		this.modelMapper = modelMapper;
		this.seatRequestRepo = seatRequestRepo;
		this.seatConfigurationRepo = seatConfigurationRepo;
	}

	@Override
	public SeatInformationViewDto seatInformationById(LocalDate bookingDate, int seatid) throws Exception {
		
		
		logger.info("[seatInformationById()] Started");
		
		SeatNumber seatNumber = this.seatNumberRepo.findById(seatid).orElseThrow(() -> new ResourceNotFoundException("Seat not found."));

		SeatConfiguration seatinfo = this.seatConfigurationRepo.findBySeatNumberAndDeletedByNull(seatNumber);
		

		
		SeatInformationViewDto seatInformationViewDto = new SeatInformationViewDto();

		if (seatinfo != null) {
			seatInformationViewDto
					.setName(seatinfo.getEmployee().getFirstName() + " " + seatinfo.getEmployee().getLastName());
			seatInformationViewDto.setDesignation(seatinfo.getEmployee().getDesignation().getDesignationName());
			seatInformationViewDto.setEmail(seatinfo.getEmployee().getEmailId());
		}

		seatInformationViewDto.setCountOfRequest(
				seatRequestRepo.countFindBySeatAndBookingDateAndDeletedDateNull(seatNumber, bookingDate));

		SeatRequest findByRequestStatus = seatRequestRepo.findByBookingDateAndSeatAndDeletedDateNull(bookingDate,
				seatNumber);

		if (findByRequestStatus == null) {
			logger.info("[seatInformationById()] Ended");
			return seatInformationViewDto;
		}
		TemporarySeatOwnerDto temp = new TemporarySeatOwnerDto();

		temp.setName(findByRequestStatus.getEmployee().getFirstName() + " "
				+ findByRequestStatus.getEmployee().getLastName());

		temp.setEmail(findByRequestStatus.getEmployee().getEmailId());
		temp.setDesignation(findByRequestStatus.getEmployee().getDesignation().getDesignationName());

		seatInformationViewDto.setTemporarySeatOwnerDto(temp);

		logger.info("[seatInformationById()] Ended");
		return seatInformationViewDto;

	}

	@Override
	public List<SeatViewDto> getSeatView(LocalDate bookingDate, Integer cityId, Integer floorId) {
		logger.info("[getSeatView()] Started");
		DayOfWeek day = bookingDate.getDayOfWeek();
		int dayId = day.getValue();

		List<SeatView> view = this.seatNumberRepo.getViewByDateAndCityAndFloorAndWorkingDay(bookingDate,
				new City(cityId), new Floor(floorId), new WorkingDay(dayId));
		view.stream()
				.filter(seatView -> seatView.getStatus().equals("Booked") || seatView.getStatus().equals("Reserved"))
				.forEach(seatView -> seatView.getSeat().setBooked(true));
		logger.info("[getSeatView()] Ended");
		return view.stream().map((seatView) -> this.modelMapper.map(seatView, SeatViewDto.class))
				.collect(Collectors.toList());
	}

}
