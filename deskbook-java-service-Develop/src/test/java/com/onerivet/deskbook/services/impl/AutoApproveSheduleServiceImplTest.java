package com.onerivet.deskbook.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Designation;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.ModeOfWork;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;
import com.onerivet.deskbook.models.payload.AcceptRejectDto;
import com.onerivet.deskbook.models.payload.BodyDto;
import com.onerivet.deskbook.models.payload.EmailDto;
import com.onerivet.deskbook.repository.SeatRequestRepo;
import com.onerivet.deskbook.services.EmailService;

import jakarta.mail.MessagingException;

@ExtendWith(MockitoExtension.class)
@EnableScheduling
class AutoApproveSheduleServiceImplTest {

	@Mock
	private SeatRequestRepo seatRequestRepo;
	@Mock
	private EmailService emailService;

	@Test
	@Scheduled(cron = "0 0 0 * * *")
	void testAcceptRequestwithin24Hours() throws MessagingException {

		LocalDate bookingDate = LocalDate.now().plusDays(1);
		Sort sort = Sort.by(Sort.Direction.ASC, "CreatedDate");

		Designation designation = Designation.builder().id(1).designationName("DEV").build();
		ModeOfWork modeOfWork = ModeOfWork.builder().id(1).modeOfWorkName("Regular").build();

		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("String")
				.profilePictureFilePath("String").modeOfWork(modeOfWork).designation(designation).build();
		int pending = 1;

		City city = City.builder().id(1).cityName("Valsad").build();
		Floor floor = Floor.builder().id(1).floorName("1st Floor").city(city).build();
		ColumnDetails column = ColumnDetails.builder().id(1).columnName("H").floor(floor).build();
		SeatNumber seat = SeatNumber.builder().id(1).seatNumber(1).column(column).build();

		LocalDateTime createdDate = LocalDateTime.now(), modifiedDate = LocalDateTime.now(),
				deletedDate = LocalDateTime.now();

		String reason = "I want to book seat";

		List<SeatRequest> pendingSeatRequest = Arrays.asList(new SeatRequest(1, employee, seat, LocalDateTime.now(),
				bookingDate, "Book seat", 1, LocalDateTime.now(), employee, LocalDateTime.now()));

		when(seatRequestRepo.findByBookingDateAndRequestStatusAndDeletedDateNull(bookingDate, pending, sort))
				.thenReturn(pendingSeatRequest);

		List<SeatRequest> pendingSeatRequestTest = seatRequestRepo
				.findByBookingDateAndRequestStatusAndDeletedDateNull(bookingDate, pending, sort);

		assertThat(pendingSeatRequestTest).isEqualTo(pendingSeatRequest);

		System.out.println(pendingSeatRequest);
		System.out.println(pendingSeatRequestTest);

		AcceptRejectDto takeAction = new AcceptRejectDto();

		takeAction.setEmailId(pendingSeatRequest.get(0).getEmployee().getEmailId());
		takeAction.setSeatId(pendingSeatRequest.get(0).getSeat().getSeatNumber());
		takeAction.setEmployeeId(pendingSeatRequest.get(0).getEmployee().getId());
		takeAction.setBookingDate(pendingSeatRequest.get(0).getBookingDate());
		takeAction.setRequestStatus(2);

		if (takeAction.getRequestStatus() == 2) {// Approve = 2

			SeatRequest requestedEmployee = new SeatRequest(pending, employee, seat, createdDate, bookingDate, reason,
					pending, modifiedDate, employee, deletedDate);

			when(seatRequestRepo.findByRequestStatusAndEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull(1,
					takeAction.getEmployeeId(), takeAction.getSeatId(), takeAction.getBookingDate()))
					.thenReturn(requestedEmployee);

			SeatRequest requestedEmployeeTest = seatRequestRepo
					.findByRequestStatusAndEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull(1,
							takeAction.getEmployeeId(), takeAction.getSeatId(), takeAction.getBookingDate());

			assertThat(requestedEmployeeTest).isEqualTo(requestedEmployee);
			System.out.println(requestedEmployee);
			System.out.println(requestedEmployeeTest);

			requestedEmployee.setRequestStatus(2);
			requestedEmployee.setModifiedDate(LocalDateTime.now());

			SeatRequest save = new SeatRequest(pending, employee, seat, createdDate, bookingDate, reason, pending,
					modifiedDate, employee, deletedDate);

			when(seatRequestRepo.save(requestedEmployeeTest)).thenReturn(save);
			SeatRequest saveTest = seatRequestRepo.save(requestedEmployee);

			assertThat(saveTest).isEqualTo(save);

			System.out.println(save);
			System.out.println(saveTest);

			if (save != null) {
				BodyDto body = new BodyDto();

				body.setEmployeeName(requestedEmployee.getEmployee().getFirstName());
				body.setBookingDate(requestedEmployee.getBookingDate());
				body.setCity(requestedEmployee.getSeat().getColumn().getFloor().getCity().getCityName());
				body.setFloorName(requestedEmployee.getSeat().getColumn().getFloor().getFloorName());
				body.setSeatNumber(requestedEmployee.getSeat().getSeatNumber());
				body.setDuration("Full Day");

				EmailDto emailDto = new EmailDto();

				emailDto.setTo(takeAction.getEmailId());
				emailDto.setSubject("Approval of Your Office Seat in the Deskbook Application System");
				emailDto.setBody(body);

				emailService.sendMailApprove(emailDto);
			}
			List<SeatRequest> rejectSeatList = Arrays.asList(new SeatRequest(1, employee, seat, LocalDateTime.now(),
					bookingDate, "Book seat", 1, LocalDateTime.now(), employee, LocalDateTime.now()));

			when(seatRequestRepo.findByEmployeeIdAndRequestStatusAndBookingDateAndDeletedDateNull(
					takeAction.getEmployeeId(), 1, takeAction.getBookingDate())).thenReturn(rejectSeatList);

			List<SeatRequest> rejectSeatListTest = seatRequestRepo
					.findByEmployeeIdAndRequestStatusAndBookingDateAndDeletedDateNull(takeAction.getEmployeeId(), 1,
							takeAction.getBookingDate());

			assertThat(rejectSeatListTest).isEqualTo(rejectSeatList);

			System.out.println(rejectSeatListTest);
			System.out.println(rejectSeatList);
			if (rejectSeatList != null) {

				for (int i = 0; i < rejectSeatList.size(); i++) {

					rejectSeatList.get(i).setRequestStatus(3);
					rejectSeatList.get(i).setModifiedDate(LocalDateTime.now());

					SeatRequest save1 = new SeatRequest(pending, employee, seat, createdDate, bookingDate, reason,
							pending, modifiedDate, employee, deletedDate);

					when(seatRequestRepo.save(rejectSeatList.get(i))).thenReturn(save1);
					SeatRequest saveTest1 = seatRequestRepo.save(rejectSeatList.get(i));

					assertThat(saveTest1).isEqualTo(save1);

					System.out.println(save1);
					System.out.println(saveTest1);
				}
			}

			List<SeatRequest> otherEmployeeRequest = Arrays
					.asList(new SeatRequest(1, employee, seat, LocalDateTime.now(), bookingDate, "Book seat", 1,
							LocalDateTime.now(), employee, LocalDateTime.now()));

			when(seatRequestRepo.getByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(1,
					takeAction.getBookingDate(), takeAction.getSeatId())).thenReturn(otherEmployeeRequest);

			List<SeatRequest> otherEmployeeRequestTest = seatRequestRepo
					.getByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(1, takeAction.getBookingDate(),
							takeAction.getSeatId());

			assertThat(otherEmployeeRequest).isEqualTo(otherEmployeeRequestTest);

			System.out.println(otherEmployeeRequest);
			System.out.println(otherEmployeeRequestTest);

			if (otherEmployeeRequest != null) {

				for (int i = 0; i < otherEmployeeRequest.size(); i++) {

					otherEmployeeRequest.get(i).setRequestStatus(3);
					otherEmployeeRequest.get(i).setModifiedDate(LocalDateTime.now());

					seatRequestRepo.save(otherEmployeeRequest.get(i));
//Call Email service for rejected seat
					EmailDto emailDto = new EmailDto();
					emailDto.setTo("xyz@gmail.com");
					emailDto.setSubject("Deskbook Application - Seat Request Rejection ");
					emailDto.setBody(new BodyDto(otherEmployeeRequest.get(i).getEmployee().getFirstName()));

					emailService.sendMailReject(emailDto);
				}
			}
		}

	}
}
