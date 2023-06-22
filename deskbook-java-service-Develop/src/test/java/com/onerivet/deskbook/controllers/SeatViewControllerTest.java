package com.onerivet.deskbook.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onerivet.deskbook.models.payload.ColumnDetailsDto;
import com.onerivet.deskbook.models.payload.SeatInformationViewDto;
import com.onerivet.deskbook.models.payload.SeatNumberDto;
import com.onerivet.deskbook.models.payload.SeatViewDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.SeatViewService;

@ExtendWith(MockitoExtension.class)
class SeatViewControllerTest {

	@Mock
	private SeatViewService seatViewService;

	@InjectMocks
	private SeatViewController seatViewController;

	@BeforeEach
	void setUp() throws Exception {
		this.seatViewController = new SeatViewController(this.seatViewService);
	}

	@Test
	void testGetSeatInformation_WithSeatIdAndDate_Returns_SeatInformation() throws Exception {

		int seatId = 1;

		SeatInformationViewDto seatInfo = new SeatInformationViewDto("Gracy Patel", "PM", "FULL DAY",
				"harsh1@gmail.com", 2, null);
		GenericResponse<SeatInformationViewDto> expectedResponse = new GenericResponse<>(seatInfo, null);

		try {
			Mockito.when(seatViewService.seatInformationById(LocalDate.now(), seatId)).thenReturn(seatInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GenericResponse<SeatInformationViewDto> actualResponse = seatViewController
				.seatInformation(LocalDate.now(), seatId);

		assertThat(actualResponse.getData()).isEqualTo(expectedResponse.getData());

	}

	@Test
	void testGetView_ReturnsSeatViewList() {

		List<SeatViewDto> viewDto = Arrays.asList(
				new SeatViewDto(new SeatNumberDto(1), new ColumnDetailsDto(1), "Reserved"),
				new SeatViewDto(new SeatNumberDto(2), new ColumnDetailsDto(2), "Booked"));

		Mockito.when(this.seatViewService.getSeatView(LocalDate.now(), 1, 1)).thenReturn(viewDto);

		GenericResponse<List<SeatViewDto>> view = this.seatViewController.getView(LocalDate.now(), 1, 1);
		System.out.println(view);

		assertThat(view.getData().containsAll(viewDto));
	}

	@Test
	void testGetView_ReturnsEmptyList() {

		Mockito.when(this.seatViewService.getSeatView(LocalDate.now(), 1, 1)).thenReturn(Collections.emptyList());

		GenericResponse<List<SeatViewDto>> view = this.seatViewController.getView(LocalDate.now(), 1, 1);
		System.out.println(view);

		assertThat(view.getData()).isEmpty();
	}

}