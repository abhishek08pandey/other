package com.onerivet.deskbook.controllers;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.deskbook.models.payload.BookingHistoryDto;
import com.onerivet.deskbook.models.response.GenericResponse;
import com.onerivet.deskbook.services.BookingHistoryService;
import com.onerivet.deskbook.util.PaginationAndSorting;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/deskbook")
public class BookingHistoryController {

    @Autowired
    private BookingHistoryService bookingHistoryService;
    
    final static Logger logger = LoggerFactory.getLogger(BookingHistoryController.class);

    public BookingHistoryController(BookingHistoryService bookingHistoryService) {
		super();
		this.bookingHistoryService = bookingHistoryService;
	}

	@GetMapping(value = {"/booking-history/{requestStatus}","/booking-history/"})
    public GenericResponse<List<BookingHistoryDto>> getBookingHistoryByStatus(Principal principal,
            PaginationAndSorting pagination, @PathVariable(required = false) Integer requestStatus) {

		logger.info("[getBookingHistoryWithStatus()] started");
		
        GenericResponse<List<BookingHistoryDto>> genericResponse = new GenericResponse<>(this.bookingHistoryService
                .getBookingRequest(principal.getName(), pagination.createPageRequest(), requestStatus), null);

        logger.info("[getBookingHistoryWithStatus()] ended");
        return genericResponse;

    }
    
    @PutMapping("/cancel-request/{requestId}")
    public GenericResponse<String> cancelBooking( Principal principal, @PathVariable int requestId) throws MessagingException {
    	
    	logger.info("[cancelBooking()] started");
        GenericResponse<String> genericResponse = new GenericResponse<>(
                this.bookingHistoryService.cancelBooking(principal.getName(), requestId), null);
        logger.info("[cancelBooking()] ended");
        return genericResponse;
        
    }
}