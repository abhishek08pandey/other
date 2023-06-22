package com.onerivet.deskbook.models.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingHistoryDto {
    
    
    
    private int requestId;
    
    private String name;

    private LocalDate bookingDate;

    private LocalDateTime requestedDate;

    private String email;

    private int floor;

    private String deskNumber;

    private int requestStatus;

}
