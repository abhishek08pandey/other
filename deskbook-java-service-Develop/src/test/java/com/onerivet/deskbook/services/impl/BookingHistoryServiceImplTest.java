package com.onerivet.deskbook.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;
import com.onerivet.deskbook.repository.EmployeeRepo;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatRequestRepo;
import com.onerivet.deskbook.services.EmailService;

import jakarta.mail.MessagingException;

@ExtendWith(MockitoExtension.class)
class BookingHistoryServiceImplTest {

	@Mock
	private EmployeeRepo employeeRepo;
	
	@Mock
	private SeatRequestRepo seatRequestRepo;

	@Mock
	private SeatConfigurationRepo seatConfigurationRepo;

	@Mock
	private Pageable pageable;

	@InjectMocks
	private BookingHistoryServiceImpl bookingHistoryServiceImpl;

	@Mock
	private EmailService emailService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetBookingRequest() {
		String employeeId = "037c1ed8-452b-4332-ba77-bbe2286ebf6a";
		SeatConfiguration seatConfiguration = new SeatConfiguration();
		seatConfiguration.setSeatNumber(new SeatNumber());
		SeatRequest seatRequest = new SeatRequest();

		Employee employee = new Employee();
		employee.setFirstName("Gracy");
		employee.setLastName("Patel");
		employee.setEmailId("harsh1@gmail.com");

		seatRequest.setId(1);
		seatRequest.setEmployee(employee);
		seatRequest.setSeat(new SeatNumber(1));
		seatRequest.setCreatedDate(LocalDateTime.now());
		seatRequest.setBookingDate(LocalDate.now());
		seatRequest.setReason("i want to book seat");
		seatRequest.setRequestStatus(1);
		seatRequest.setModifiedBy(employee);
		seatRequest.setModifiedDate(LocalDateTime.now());
		seatRequest.setDeletedDate(LocalDateTime.now());

		List<SeatRequest> seatRequestList = new ArrayList<>();
		seatRequestList.add(seatRequest);
		when(seatConfigurationRepo.findByEmployeeAndDeletedByNull(any(Employee.class))).thenReturn(seatConfiguration);

		SeatConfiguration seatConfigurationTest = seatConfigurationRepo.findByEmployeeAndDeletedByNull(employee);

		assertThat(seatConfiguration).isEqualTo(seatConfigurationTest);

		System.out.println(seatConfiguration);
		System.out.println(seatConfigurationTest);
		System.out.println(seatRequest);
	}

	@Test
	void cancelBooking_ValidEmployeeIdAndRequestId_ReturnsCancellationMessage() throws MessagingException {

		String employeeId = "12345";
		int requestId = 123;
		SeatRequest seatRequest = new SeatRequest();
		seatRequest.setEmployee(new Employee());
		seatRequest.getEmployee().setFirstName("John");
		seatRequest.setBookingDate(LocalDate.now());

		when(seatRequestRepo.findById(requestId)).thenReturn(Optional.of(seatRequest));

		String result = bookingHistoryServiceImpl.cancelBooking(employeeId, requestId);

		assertEquals("Your seat has been cancelled successfully", result);
		assertEquals(4, seatRequest.getRequestStatus());
		assertNotNull(seatRequest.getDeletedDate());
		verify(seatRequestRepo, times(1)).save(seatRequest);

		System.out.println(result);

	}
}
