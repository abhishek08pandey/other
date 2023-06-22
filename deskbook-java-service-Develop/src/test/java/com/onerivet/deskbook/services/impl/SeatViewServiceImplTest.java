package com.onerivet.deskbook.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.onerivet.deskbook.models.entity.City;
import com.onerivet.deskbook.models.entity.ColumnDetails;
import com.onerivet.deskbook.models.entity.Designation;
import com.onerivet.deskbook.models.entity.Employee;
import com.onerivet.deskbook.models.entity.Floor;
import com.onerivet.deskbook.models.entity.SeatConfiguration;
import com.onerivet.deskbook.models.entity.SeatNumber;
import com.onerivet.deskbook.models.entity.SeatRequest;
import com.onerivet.deskbook.models.entity.SeatView;
import com.onerivet.deskbook.models.entity.WorkingDay;
import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.models.payload.SeatViewDto;
import com.onerivet.deskbook.repository.SeatConfigurationRepo;
import com.onerivet.deskbook.repository.SeatNumberRepo;
import com.onerivet.deskbook.repository.SeatRequestRepo;

@ExtendWith(MockitoExtension.class)
class SeatViewServiceImplTest {

	@Mock
	private SeatNumberRepo seatNumberRepo;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private SeatViewServiceImpl seatViewServiceImpl;

	@Mock
	private SeatConfigurationRepo seatConfigurationRepo;

	@Mock
	private SeatRequestRepo seatRequestRepo;


	@BeforeEach
	void setUp() throws Exception {
		this.seatViewServiceImpl = new SeatViewServiceImpl(this.seatNumberRepo, this.modelMapper, this.seatRequestRepo,
				this.seatConfigurationRepo);
		Mockito.mock(SeatConfiguration.class);
		Mockito.mock(Employee.class);
		Mockito.mock(SeatNumber.class);
		Mockito.mock(SeatRequest.class);
	}

	@Test
	void testGetSeatInformationById_WithSeatIdAndDate_Returns_SeatInformationViewDto() throws Exception {

		Mockito.mock(SeatInformationViewDto.class);
		

		int seatId = 1;
		LocalDate bookingDate = LocalDate.now();
		

		SeatNumber seatNumber = SeatNumber.builder().id(1).build();

		Employee employee = Employee.builder().id("2fggdsf").firstName("drashti").lastName("balsara")
				.designation(new Designation(1, "Developer")).emailId("drashti@gmail.com").build();
		SeatConfiguration seatInfo = SeatConfiguration.builder().seatNumber(seatNumber).employee(employee).build();
		SeatInformationViewDto expectedDto = SeatInformationViewDto.builder().name("drashti balsara").designation("Developer")
				.duration("fullday").email("drashti@gmail.com").countOfRequest(2).temporarySeatOwnerDto(null).build();

		SeatRequest seatRequest = SeatRequest.builder().employee(employee).build();

		when(seatNumberRepo.findById(seatId)).thenReturn(Optional.of(seatNumber));
		when(seatConfigurationRepo.findBySeatNumberAndDeletedByNull(seatNumber)).thenReturn(seatInfo);
		when(seatRequestRepo.countFindBySeatAndBookingDateAndDeletedDateNull(seatNumber, bookingDate)).thenReturn(2);
		when(seatRequestRepo.findByBookingDateAndSeatAndDeletedDateNull(bookingDate, seatNumber))
				.thenReturn(seatRequest);


		SeatInformationViewDto result = seatViewServiceImpl.seatInformationById(bookingDate,seatId);

		Assertions.assertEquals(expectedDto.getName(), result.getName());
		Assertions.assertEquals(expectedDto.getDesignation(), result.getDesignation());
		Assertions.assertEquals(expectedDto.getEmail(), result.getEmail());
		Assertions.assertEquals(expectedDto.getCountOfRequest(), result.getCountOfRequest());

		verify(seatNumberRepo, times(1)).findById(seatId);
		verify(seatConfigurationRepo, times(1)).findBySeatNumberAndDeletedByNull(seatNumber);
		verify(seatRequestRepo, times(1)).countFindBySeatAndBookingDateAndDeletedDateNull(seatNumber, bookingDate);
		verify(seatRequestRepo, times(1)).findByBookingDateAndSeatAndDeletedDateNull(bookingDate, seatNumber);
	}

	@Test
	void testGetSeatView_WithLocalDateAndCityAndFloot_ReturnsSeatViewList() {

		List<SeatView> view = Arrays.asList(new SeatView(new SeatNumber(1), new ColumnDetails(1), "Reserved"),
				new SeatView(new SeatNumber(2), new ColumnDetails(2), "Booked"));

		List<SeatViewDto> viewDto = Arrays.asList(
				new SeatViewDto(new SeatNumberDto(1), new ColumnDetailsDto(1), "Reserved"),
				new SeatViewDto(new SeatNumberDto(2), new ColumnDetailsDto(2), "Booked"));

		assertThat(view.get(0).getSeat().getId()).isEqualTo(viewDto.get(0).getSeat().getId());
		assertThat(view.get(0).getColumn().getId()).isEqualTo(viewDto.get(0).getColumn().getId());
		assertThat(view.get(0).getStatus()).isEqualTo(viewDto.get(0).getStatus());

		assertThat(view.get(1).getSeat().getId()).isEqualTo(viewDto.get(1).getSeat().getId());
		assertThat(view.get(1).getColumn().getId()).isEqualTo(viewDto.get(1).getColumn().getId());
		assertThat(view.get(1).getStatus()).isEqualTo(viewDto.get(1).getStatus());

		when(this.seatNumberRepo.getViewByDateAndCityAndFloorAndWorkingDay(any(LocalDate.class), any(City.class),
				any(Floor.class), any(WorkingDay.class))).thenReturn(view);

		when(this.modelMapper.map(view.get(0), SeatViewDto.class)).thenReturn(viewDto.get(0));
		when(this.modelMapper.map(view.get(1), SeatViewDto.class)).thenReturn(viewDto.get(1));

		List<SeatViewDto> seatView = this.seatViewServiceImpl.getSeatView(LocalDate.now(), 1, 1);

		System.out.println(seatView);
		assertThat(seatView).containsExactly(viewDto.get(0), viewDto.get(1));
	}

	@Test
	void testGetSeatView_WithLocalDateAndCityAndFloot_ReturnsEmptyList() {

		when(this.seatNumberRepo.getViewByDateAndCityAndFloorAndWorkingDay(any(LocalDate.class), any(City.class),
				any(Floor.class), any(WorkingDay.class))).thenReturn(Collections.emptyList());

		List<SeatViewDto> seatView = this.seatViewServiceImpl.getSeatView(LocalDate.now(), 1, 1);

		System.out.println(seatView);
		assertThat(seatView).isEmpty();
	}

}