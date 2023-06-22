package com.onerivet.deskbook.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import com.onerivet.deskbook.models.payload.BookingHistoryDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.BookingHistoryService;
import com.onerivet.deskbook.util.PaginationAndSorting;

import jakarta.mail.MessagingException;

@ExtendWith(MockitoExtension.class)
class BookingHistoryControllerTest {

	@Mock
	private BookingHistoryService bookingHistoryService;

	@InjectMocks
	private BookingHistoryController bookingHistoryController;

	@Mock
	private Principal principal;

	@BeforeEach
	void setUp() throws Exception {
		this.bookingHistoryController = new BookingHistoryController(this.bookingHistoryService);
	}

	@Test
	void testGetBookingHistoryByStatus() {
		int requestStatus = 0;
		PaginationAndSorting pagination = new PaginationAndSorting();
		List<BookingHistoryDto> bookingHistory = new ArrayList<>();
		bookingHistory.add(new BookingHistoryDto(1, "Jacky Patel", LocalDate.of(2023, 06, 16), LocalDateTime.now(),
				"jackypatel@gmail.com", 1, "A1", requestStatus));
		System.out.println(bookingHistory);

		when(this.bookingHistoryService.getBookingRequest(principal.getName(),
				PageRequest.of(pagination.getPage(), pagination.getSize()), requestStatus)).thenReturn(bookingHistory);

		GenericResponse<List<BookingHistoryDto>> actualResponse = bookingHistoryController
				.getBookingHistoryByStatus(principal, pagination, requestStatus);
		System.out.println(actualResponse);


		assertThat(actualResponse.getData().containsAll(bookingHistory));

	}
	
    @Test
    void testCancelBooking() throws MessagingException {
        int requestId = 1;
        String expectedResponse = "Booking cancelled";

        Mockito.when(bookingHistoryService.cancelBooking(principal.getName(), requestId))
                .thenReturn(expectedResponse);

        GenericResponse<String> actualResponse = bookingHistoryController
                .cancelBooking(principal, requestId);

        assertThat(actualResponse.getData()).isEqualTo(expectedResponse);
    }


}
