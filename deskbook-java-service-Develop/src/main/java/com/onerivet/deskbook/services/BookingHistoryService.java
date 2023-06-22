package com.onerivet.deskbook.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.onerivet.deskbook.models.payload.BookingHistoryDto;

import jakarta.mail.MessagingException;

public interface BookingHistoryService {

    public List<BookingHistoryDto> getBookingRequest(String employeeId, Pageable pageable, Integer requestStatus);
    
    public String cancelBooking(String employeeId,int requestId) throws MessagingException;
}