package com.onerivet.deskbook.models.payload;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class AcceptRejectDto {
      
        private String employeeId;// requested employee
       
        @JsonFormat(pattern = "MM/dd/yyyy")
        private  LocalDate bookingDate;
            
        private int seatId;
        
        private int requestStatus;//Approve = 2, reject = 3
        
        private String emailId;
        
        private int floor;
           
    }