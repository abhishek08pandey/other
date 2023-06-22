package com.onerivet.deskbook.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;

@ExtendWith(MockitoExtension.class)
class SeatRequestRepoTest {

	@Mock
	private SeatRequestRepo seatRequestRepo;

	@Test
	public void testCountFindBySeatAndBookingDateAndDeletedDateNull() {
		SeatNumber seatId = new SeatNumber(123);
		LocalDate bookingDate = LocalDate.of(2023, 6, 15);
		int expectedResult = 5; 
		when(seatRequestRepo.countFindBySeatAndBookingDateAndDeletedDateNull(seatId, bookingDate))
				.thenReturn(expectedResult);

		int actualResult = seatRequestRepo.countFindBySeatAndBookingDateAndDeletedDateNull(seatId, bookingDate);

		Assertions.assertEquals(expectedResult, actualResult);
		System.out.println(expectedResult);
		System.out.println(actualResult);
	}

	@Test
	void findByEmployeeIdAndSeatAndBookingDateAndDeletedDateNull() {

		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("null").profilePictureFilePath("null")
				.modeOfWork(null).designation(null).build();

		String employeeId = "00763866-dc7d-429d-91f3-c8bb5c10891a";
		SeatNumber seatId = new SeatNumber();
		LocalDate bookingDate = LocalDate.of(2023, 6, 12);

		SeatRequest expectedSeatRequest = new SeatRequest();
		expectedSeatRequest.setId(1);
		expectedSeatRequest.setEmployee(employee);
		expectedSeatRequest.setSeat(seatId);
		expectedSeatRequest.setBookingDate(bookingDate);
		expectedSeatRequest.setDeletedDate(null);

		when(seatRequestRepo.findByEmployeeIdAndSeatAndBookingDateAndDeletedDateNull(employeeId, seatId, bookingDate))
				.thenReturn(expectedSeatRequest);
		System.out.println(expectedSeatRequest);
		SeatRequest actualSeatRequest = seatRequestRepo
				.findByEmployeeIdAndSeatAndBookingDateAndDeletedDateNull(employeeId, seatId, bookingDate);
		System.out.println(actualSeatRequest);
		assertEquals(expectedSeatRequest, actualSeatRequest);
	}

	@Test
	void findByBookingDateAndSeatAndDeletedDateNull() {

		LocalDate bookingDate = LocalDate.of(2023, 6, 12);
		SeatNumber seatNumber = new SeatNumber();
		SeatRequest expectedSeatRequest = new SeatRequest();
		expectedSeatRequest.setId(1);
		expectedSeatRequest.setBookingDate(bookingDate);
		expectedSeatRequest.setSeat(seatNumber);
		expectedSeatRequest.setDeletedDate(null);

		when(seatRequestRepo.findByBookingDateAndSeatAndDeletedDateNull(bookingDate, seatNumber))
				.thenReturn(expectedSeatRequest);

		SeatRequest actualSeatRequest = seatRequestRepo.findByBookingDateAndSeatAndDeletedDateNull(bookingDate,
				seatNumber);

		assertEquals(expectedSeatRequest, actualSeatRequest);
		System.out.println(actualSeatRequest);
		System.out.println(expectedSeatRequest);
	}

	@Test
	void findSeatRequestBySeat() {
		SeatNumber seatNumber = new SeatNumber();

		List<SeatRequest> expectedSeatRequests = new ArrayList<>();
		SeatRequest seatRequest1 = new SeatRequest();
		seatRequest1.setId(1);
		seatRequest1.setSeat(seatNumber);
		seatRequest1.setBookingDate(LocalDate.of(2023, 6, 12));
		expectedSeatRequests.add(seatRequest1);

		SeatRequest seatRequest2 = new SeatRequest();
		seatRequest2.setId(2);
		seatRequest2.setSeat(seatNumber);
		seatRequest2.setBookingDate(LocalDate.of(2023, 6, 10));
		expectedSeatRequests.add(seatRequest2);

		Pageable pageable = Pageable.ofSize(10).withPage(0);

		when(seatRequestRepo.findSeatRequestBySeat(seatNumber, pageable)).thenReturn(expectedSeatRequests);

		List<SeatRequest> actualSeatRequests = seatRequestRepo.findSeatRequestBySeat(seatNumber, pageable);

		assertEquals(expectedSeatRequests, actualSeatRequests);

		System.out.println(expectedSeatRequests);
		System.out.println(actualSeatRequests);
	}

	@Test
	public void testFindSeatRequestBySeatAndRequestStatus() {
		SeatNumber seatId = new SeatNumber(123);
		int requestStatus = 2;
		List<SeatRequest> expectedResult = new ArrayList<>();

		when(seatRequestRepo.findSeatRequestBySeatAndRequestStatus(seatId, Pageable.unpaged(), requestStatus))
				.thenReturn(expectedResult);

		List<SeatRequest> actualResult = seatRequestRepo.findSeatRequestBySeatAndRequestStatus(seatId,
				Pageable.unpaged(), requestStatus);

		Assertions.assertEquals(expectedResult, actualResult);
		System.out.println(expectedResult);
		System.out.println(actualResult);
	}

	@Test
	void getByFirstNameOrLastName() {
		String firstName = "sonali";
		String lastName = "gupta";
		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("null").profilePictureFilePath("null")
				.modeOfWork(null).designation(null).build();

		List<SeatRequest> expectedSeatRequests = new ArrayList<>();
		SeatRequest seatRequest1 = new SeatRequest();
		seatRequest1.setId(1);
		seatRequest1.setEmployee(employee);
		expectedSeatRequests.add(seatRequest1);

		SeatRequest seatRequest2 = new SeatRequest();
		seatRequest2.setId(2);
		seatRequest2.setEmployee(employee);
		expectedSeatRequests.add(seatRequest2);

		Pageable pageable = Pageable.ofSize(10).withPage(0);

		when(seatRequestRepo.getByFirstNameOrLastName(firstName, lastName, pageable)).thenReturn(expectedSeatRequests);

		List<SeatRequest> actualSeatRequests = seatRequestRepo.getByFirstNameOrLastName(firstName, lastName, pageable);

		assertEquals(expectedSeatRequests, actualSeatRequests);
		System.out.println(expectedSeatRequests);
		System.out.println(actualSeatRequests);
	}

	@Test
	void alreadyBookedOrNot() {
		SeatNumber seat = new SeatNumber();
		LocalDate requestDate = LocalDate.now();
		Employee employee = new Employee();

		SeatRequest expectedSeatRequest = new SeatRequest();
		expectedSeatRequest.setId(1);
		expectedSeatRequest.setSeat(seat);
		expectedSeatRequest.setBookingDate(requestDate);
		expectedSeatRequest.setEmployee(employee);

		when(seatRequestRepo.alreadyBookedOrNot(seat, requestDate, employee)).thenReturn(expectedSeatRequest);

		SeatRequest actualSeatRequest = seatRequestRepo.alreadyBookedOrNot(seat, requestDate, employee);

		assertEquals(expectedSeatRequest, actualSeatRequest);
		System.out.println(expectedSeatRequest);
		System.out.println(actualSeatRequest);
	}
	
	@Test
	void findSeatsByEmployeeId()
	{
		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("null").profilePictureFilePath("null")
				.modeOfWork(null).designation(null).build();


		List<SeatRequest> expectedSeatRequests = new ArrayList<>();
		SeatRequest seatRequest1 = new SeatRequest();
		seatRequest1.setId(1);
		seatRequest1.setEmployee(employee);
		SeatRequest seatRequest2 = new SeatRequest();
		seatRequest2.setId(2);
		seatRequest2.setEmployee(employee);
		expectedSeatRequests.add(seatRequest1);
		expectedSeatRequests.add(seatRequest2);

		when(seatRequestRepo.findSeatsByEmployeeId(employee)).thenReturn(expectedSeatRequests);

		List<SeatRequest> actualSeatRequests = seatRequestRepo.findSeatsByEmployeeId(employee);

		assertEquals(expectedSeatRequests, actualSeatRequests);
		System.out.println(actualSeatRequests);
		System.out.println(expectedSeatRequests);
	}
	
	
	@Test
    public void testFindEmployeeRequestForDay() {
		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("null").profilePictureFilePath("null")
				.modeOfWork(null).designation(null).build();
        LocalDate bookingDate = LocalDate.now();
        int expectedResult = 5; 

        when(seatRequestRepo.findEmployeeRequestForDay(any(Employee.class), any(LocalDate.class)))
                .thenReturn(expectedResult);

        int actualResult = seatRequestRepo.findEmployeeRequestForDay(employee, bookingDate);
        Assertions.assertEquals(expectedResult, actualResult);
        System.out.println(expectedResult);
        System.out.println(actualResult);
    }
	

	
	@Test
	void getBookingHistoryByEmployee()
	{
		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("null").profilePictureFilePath("null")
				.modeOfWork(null).designation(null).build();


		List<SeatRequest> expectedBookingHistory = new ArrayList<>();
		SeatRequest booking1 = new SeatRequest();
		booking1.setId(1);
		booking1.setEmployee(employee);
		SeatRequest booking2 = new SeatRequest();
		booking2.setId(2);
		booking2.setEmployee(employee);
		expectedBookingHistory.add(booking1);
		expectedBookingHistory.add(booking2);

		when(seatRequestRepo.getBookingHistoryByEmployee(employee, Pageable.unpaged())).thenReturn(expectedBookingHistory);

		List<SeatRequest> actualBookingHistory = seatRequestRepo.getBookingHistoryByEmployee(employee, Pageable.unpaged());

		assertEquals(expectedBookingHistory, actualBookingHistory);
		System.out.println(expectedBookingHistory);
		System.out.println(actualBookingHistory);
	}
	
	@Test
	void getBookingHistoryByEmployeeAndRequestStatus()
	{
		
		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("null").profilePictureFilePath("null")
				.modeOfWork(null).designation(null).build();

		List<SeatRequest> expectedBookingHistory = new ArrayList<>();
		SeatRequest booking1 = new SeatRequest();
		booking1.setId(1);
		booking1.setEmployee(employee);
		SeatRequest booking2 = new SeatRequest();
		booking2.setId(2);
		booking2.setEmployee(employee);
		expectedBookingHistory.add(booking1);
		expectedBookingHistory.add(booking2);

		when(seatRequestRepo.getBookingHistoryByEmployeeAndRequestStatus(employee, Pageable.unpaged(), 2)).thenReturn(expectedBookingHistory);

		List<SeatRequest> actualBookingHistory = seatRequestRepo.getBookingHistoryByEmployeeAndRequestStatus(employee, Pageable.unpaged(), 2);
		System.out.println(expectedBookingHistory);
		System.out.println(actualBookingHistory);

		assertEquals(expectedBookingHistory, actualBookingHistory);
	}


	@Test
	void testFindByRequestStatusAndEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull() {

		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("null").profilePictureFilePath("null")
				.modeOfWork(null).designation(null).build();

		int pending = 1;
		String employeeId = "00763866-dc7d-429d-91f3-c8bb5c10891a";
		SeatNumber seatId = new SeatNumber();
		// seatId=seatId.builder().id(1).seatNumber(2).column(new
		// ColumnDetails()).isAvailable(false).booked(false);
		LocalDate bookingDate = LocalDate.of(2023, 6, 11);

		SeatRequest expectedSeatRequest = new SeatRequest();
		expectedSeatRequest.setId(1);
		expectedSeatRequest.setRequestStatus(pending);
		expectedSeatRequest.setEmployee(employee);
		expectedSeatRequest.setSeat(seatId);
		expectedSeatRequest.setBookingDate(bookingDate);
		expectedSeatRequest.setDeletedDate(null);
		System.out.println(expectedSeatRequest);
		when(seatRequestRepo.findByRequestStatusAndEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull(pending,
				employeeId, seatId, bookingDate)).thenReturn(expectedSeatRequest);

		SeatRequest actualSeatRequest = seatRequestRepo
				.findByRequestStatusAndEmployeeIdAndSeatIdAndBookingDateAndDeletedDateNull(pending, employeeId, seatId,
						bookingDate);
		System.out.println(actualSeatRequest);
		assertEquals(expectedSeatRequest, actualSeatRequest);
	}

	@Test
	void testGetByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull() {
		// fail("Not yet implemented");
		LocalDate bookingDate = LocalDate.of(2023, 6, 12);
		int requestStatus = 1;
		SeatNumber seatId = new SeatNumber(1);

		SeatRequest seatRequest1 = new SeatRequest();
		seatRequest1.setId(1);
		seatRequest1.setBookingDate(bookingDate);
		seatRequest1.setRequestStatus(requestStatus);
		seatRequest1.setSeat(seatId);
		seatRequest1.setReason("reason1");

		SeatRequest seatRequest2 = new SeatRequest();
		seatRequest2.setId(2);
		seatRequest2.setBookingDate(bookingDate);
		seatRequest2.setRequestStatus(requestStatus);
		seatRequest2.setSeat(seatId);
		seatRequest2.setReason("reason2");

		List<SeatRequest> expectedSeatRequests = new ArrayList<>();
		expectedSeatRequests.add(seatRequest1);
		expectedSeatRequests.add(seatRequest2);
		System.out.println(expectedSeatRequests);
		when(seatRequestRepo.getByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(1, LocalDate.of(2023, 6, 12),
				seatId)).thenReturn(expectedSeatRequests);

		List<SeatRequest> actualSeatRequests = seatRequestRepo
				.getByRequestStatusAndBookingDateAndSeatIdAndDeletedDateNull(1, LocalDate.of(2023, 6, 12), seatId);

		System.out.println(actualSeatRequests);
		assertEquals(expectedSeatRequests, actualSeatRequests);
	}

	@Test
	void testFindByEmployeeIdAndRequestStatusAndBookingDateAndDeletedDateNull() {
		Employee employee = new Employee("00763866-dc7d-429d-91f3-c8bb5c10891a");
		LocalDate bookingDate = LocalDate.of(2023, 6, 12);
		int requestStatus = 1;

		SeatRequest seatRequest1 = new SeatRequest();
		seatRequest1.setId(1);
		seatRequest1.setEmployee(employee);
		seatRequest1.setBookingDate(bookingDate);
		seatRequest1.setRequestStatus(requestStatus);
		SeatNumber seatNumber = new SeatNumber();
		seatRequest1.setSeat(seatNumber);
		seatRequest1.setReason("reason1");

		SeatRequest seatRequest2 = new SeatRequest();
		seatRequest2.setId(2);
		seatRequest2.setEmployee(employee);
		seatRequest2.setBookingDate(bookingDate);
		seatRequest2.setRequestStatus(requestStatus);
		seatRequest2.setSeat(new SeatNumber());
		seatRequest2.setReason("reason2");

		List<SeatRequest> expectedSeatRequests = new ArrayList<>();
		expectedSeatRequests.add(seatRequest1);
		expectedSeatRequests.add(seatRequest2);

		when(seatRequestRepo.findByEmployeeIdAndRequestStatusAndBookingDateAndDeletedDateNull(
				"00763866-dc7d-429d-91f3-c8bb5c10891a", 1, LocalDate.of(2023, 6, 12))).thenReturn(expectedSeatRequests);
		System.out.println(expectedSeatRequests);

		List<SeatRequest> actualSeatRequests = seatRequestRepo
				.findByEmployeeIdAndRequestStatusAndBookingDateAndDeletedDateNull(
						"00763866-dc7d-429d-91f3-c8bb5c10891a", 1, LocalDate.of(2023, 6, 12));
		System.out.println(actualSeatRequests);
		assertEquals(expectedSeatRequests, actualSeatRequests);
	}
	
	@Test
	void findUnAssignedSeat()
	{
		Employee employee = new Employee();
		employee = Employee.builder().id("ygjvb76-jhgj6-hk6fgh").emailId("xyz@gmail.com").firstName("xyz")
				.lastName("abc").phoneNumber("7778889994").profilePictureFileName("null").profilePictureFilePath("null")
				.modeOfWork(null).designation(null).build();

        List<SeatNumber> expectedUnassignedSeats = new ArrayList<>();
        SeatNumber seat1 = new SeatNumber();
        seat1.setId(1);
        seat1.setSeatNumber(1);
        SeatNumber seat2 = new SeatNumber();
        seat2.setId(2);
        seat2.setSeatNumber(2);
        expectedUnassignedSeats.add(seat1);
        expectedUnassignedSeats.add(seat2);

        when(seatRequestRepo.findUnAssignedSeat(employee)).thenReturn(expectedUnassignedSeats);

        List<SeatNumber> actualUnassignedSeats = seatRequestRepo.findUnAssignedSeat(employee);

        assertEquals(expectedUnassignedSeats, actualUnassignedSeats);
        System.out.println(expectedUnassignedSeats);
        System.out.println(actualUnassignedSeats);
    }

	@Test
	void testFindByBookingDateAndRequestStatusAndDeletedDateNull() {
		// fail("Not yet implemented");
		LocalDate bookingDate = LocalDate.of(2023, 6, 12);
		int requestStatus = 1;
		Sort sort = Sort.by(Sort.Direction.ASC, "CreatedDate");
		// Sort sort = Sort.by("id").ascending();

		SeatRequest seatRequest1 = new SeatRequest();
		seatRequest1.setId(1);
		seatRequest1.setBookingDate(bookingDate);
		seatRequest1.setRequestStatus(requestStatus);
		seatRequest1.setReason("reason1");

		SeatRequest seatRequest2 = new SeatRequest();
		seatRequest2.setId(2);
		seatRequest2.setBookingDate(bookingDate);
		seatRequest2.setRequestStatus(requestStatus);
		seatRequest2.setReason("reason2");

		List<SeatRequest> expectedSeatRequests = new ArrayList<>();
		expectedSeatRequests.add(seatRequest1);
		expectedSeatRequests.add(seatRequest2);

		when(seatRequestRepo.findByBookingDateAndRequestStatusAndDeletedDateNull(bookingDate, requestStatus, sort))
				.thenReturn(expectedSeatRequests);
		System.out.println(expectedSeatRequests);
		List<SeatRequest> actualSeatRequests = seatRequestRepo
				.findByBookingDateAndRequestStatusAndDeletedDateNull(bookingDate, requestStatus, sort);

		assertEquals(expectedSeatRequests, actualSeatRequests);
		System.out.println(actualSeatRequests);
	}

}