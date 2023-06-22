package com.onerivet.deskbook.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onerivet.deskbook.exception.ResourceNotFoundException;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;
import com.onerivet.deskbook.models.payload.BodyDto;
import com.onerivet.deskbook.models.payload.BookingHistoryDto;
import com.onerivet.deskbook.models.payload.EmailDto;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatRequestRepo;
import com.onerivet.deskbook.services.BookingHistoryService;
import com.onerivet.deskbook.services.EmailService;

import jakarta.mail.MessagingException;

@Transactional
@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {
	@Autowired
	private SeatConfigurationRepo seatConfigurationRepo;

	@Autowired
	private SeatRequestRepo seatRequestRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private EmailService emailService;

	final static Logger logger = LoggerFactory.getLogger(BookingHistoryServiceImpl.class);
	
	@Override
	public List<BookingHistoryDto> getBookingRequest(String employeeId, Pageable pageable, Integer requestStatus) {
		logger.info("[getBookingRequest()] started");
		List<SeatRequest> seats;
		
		Employee emp = employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
		if(emp.getModeOfWork().getId() == 1) {
			logger.info("[getBookingRequest()] ended");

			return Collections.emptyList();
		}

		if (requestStatus == null) {
			seats = seatRequestRepo.getBookingHistoryByEmployee(new Employee(employeeId), pageable);
		} else {
			seats = seatRequestRepo.getBookingHistoryByEmployeeAndRequestStatus(new Employee(employeeId), pageable,
					requestStatus);
		}

		List<SeatNumber> unassignedSeats = seatRequestRepo.findUnAssignedSeat(new Employee(employeeId));

		List<BookingHistoryDto> bookingHistoryDtos = new ArrayList<>();

		for (int i = 0; i < seats.size(); i++) {

			if (unassignedSeats.contains(seats.get(i).getSeat())) {
				System.out.println(seats.get(i).getSeat().getSeatNumber());
				BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
				bookingHistoryDto.setName("Pooja Parmar");
				bookingHistoryDto.setEmail("pooja.parmar@1rivet.com");
				bookingHistoryDto.setRequestStatus(seats.get(i).getRequestStatus());
				bookingHistoryDto.setFloor(seats.get(i).getSeat().getColumn().getFloor().getId());
				bookingHistoryDto.setDeskNumber(seats.get(i).getSeat().getColumn().getColumnName() + ""
						+ seats.get(i).getSeat().getSeatNumber());
				bookingHistoryDto.setBookingDate(seats.get(i).getBookingDate());
				bookingHistoryDto.setRequestedDate(seats.get(i).getCreatedDate());
				bookingHistoryDto.setRequestId(seats.get(i).getId());

				bookingHistoryDtos.add(bookingHistoryDto);

			} else {
				SeatConfiguration seatConfiguration = seatConfigurationRepo
						.findBySeatNumberAndDeletedByNull(seats.get(i).getSeat());

				BookingHistoryDto bookingHistoryDto = new BookingHistoryDto();
				bookingHistoryDto.setName(seatConfiguration.getEmployee().getFirstName() + " "
						+ seatConfiguration.getEmployee().getLastName());
				bookingHistoryDto.setDeskNumber(seatConfiguration.getSeatNumber().getColumn().getColumnName() + ""
						+ seatConfiguration.getSeatNumber().getSeatNumber());
				bookingHistoryDto.setRequestId(seats.get(i).getId());
				bookingHistoryDto.setRequestStatus(seats.get(i).getRequestStatus());
				bookingHistoryDto.setRequestedDate(seats.get(i).getCreatedDate());
				bookingHistoryDto.setEmail(seatConfiguration.getEmployee().getEmailId());
				bookingHistoryDto.setBookingDate(seats.get(i).getBookingDate());
				bookingHistoryDto.setFloor(seatConfiguration.getSeatNumber().getColumn().getFloor().getId());

				bookingHistoryDtos.add(bookingHistoryDto);
			}
		}
		logger.info("[getBookingRequest()] ended");
		return bookingHistoryDtos;
	}

	@Override
	public String cancelBooking(String employeeId, int requestId) throws MessagingException {
		logger.info("[cancelBooking()] started");
		SeatRequest seatRequest = seatRequestRepo.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("Data not found."));
		seatRequest.setRequestStatus(4);
		seatRequest.setDeletedDate(LocalDateTime.now());
		seatRequestRepo.save(seatRequest);
		
		SeatConfiguration seatConfiguration = seatConfigurationRepo
				.findBySeatNumberAndDeletedByNull(seatRequest.getSeat());
		
		EmailDto emailDto = new EmailDto();
		emailDto.setSubject("Office Seat Cancellation Notification ");
		
		if (seatConfiguration == null) {
			// Owner = null, Email send Admin
			emailDto.setTo("pooja.parmar@1rivet.com");// pooja
			emailDto.setBody(new BodyDto("Pooja", seatRequest.getBookingDate()));// Admin name

			emailService.sendMailCancel(emailDto);
			
		} else {
			// Owner Employee
			emailDto.setTo(seatConfiguration.getEmployee().getEmailId());
			emailDto.setBody(
					new BodyDto(seatConfiguration.getEmployee().getFirstName(), seatRequest.getBookingDate()));

			emailService.sendMailCancel(emailDto);
		}
		logger.info("[cancelBooking()] ended");
		return "Your seat has been cancelled successfully";
	}
}