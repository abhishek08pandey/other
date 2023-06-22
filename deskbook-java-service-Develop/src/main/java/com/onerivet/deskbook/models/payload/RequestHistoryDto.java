package com.onerivet.deskbook.models.payload;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RequestHistoryDto {
    
    
    private int seatId;
    
    private String employeeId;
    
    private String name;
    
    private LocalDateTime requestDate;
    
    private  LocalDate requestFor;
    
    private String emailId;
    
    private int floorNo;
    
    private String deskNo;
    
    private int status;
    
    private String reason;
        
}
